package com.germani.dit.node.core.service.impl

import com.germani.dit.node.core.model.exception.MalformedChainConfigurationException
import com.germani.dit.node.core.model.preprocessing.ChainDefinition
import com.germani.dit.node.core.model.preprocessing.NodeDefinition
import com.germani.dit.node.core.model.structure.ChainUnitStructure
import com.germani.dit.node.core.service.ChainDefinitionPreprocessor
import com.germani.dit.node.core.service.ChainStructureProvider
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.concurrent.ConcurrentHashMap

@Service
class CacheableChainDefinitionPreprocessor(
    private var nodeChainProvider: ChainStructureProvider
) : ChainDefinitionPreprocessor {

    private val cache: MutableMap<String, ChainDefinition> = ConcurrentHashMap()

    override fun process(id: String): Mono<ChainDefinition> =
        if (cache.containsKey(id)) {
            Mono.just(cache[id]!!)
        } else {
            //TODO: validation should be here
            nodeChainProvider.provide(id).map {
                val initNodeDefinition = mapToDefinition(it.initNode)
                val internalIdToNode = mutableMapOf<Long, NodeDefinition>()
                fillParents(internalIdToNode, initNodeDefinition, null)

                val terminatingNode = internalIdToNode.entries
                    .find { entry -> entry.value.isTerminating() }?.value
                    ?: throw MalformedChainConfigurationException("Terminating node should be dined for the chain")

                ChainDefinition(id, initNodeDefinition, terminatingNode)
            }
        }


    private fun mapToDefinition(node: ChainUnitStructure): NodeDefinition {
        val nodeDefinition = NodeDefinition(internalId = node.internalId, nodeId = node.nodeId)
        node.childs.forEach {
            nodeDefinition.childs.add(mapToDefinition(it))
        }
        return nodeDefinition
    }

    private fun fillParents(
        internalIdToNode: MutableMap<Long, NodeDefinition>,
        node: NodeDefinition,
        parentNode: NodeDefinition? = null
    ) {
        internalIdToNode.putIfAbsent(node.internalId, node)
        parentNode?.let {
            internalIdToNode[node.internalId]!!.parents.add(parentNode)
        }
        node.childs.forEach {
            fillParents(internalIdToNode, it, node)
        }
    }

}