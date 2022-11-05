package com.germani.dit.node.core.service.impl

import com.germani.dit.node.core.model.exception.InconsistentGraphStateException
import com.germani.dit.node.core.model.exception.MalformedGraphConfigurationException
import com.germani.dit.node.core.model.node.preprocessing.GraphDefinition
import com.germani.dit.node.core.model.node.preprocessing.NodeDefinition
import com.germani.dit.node.core.model.node.GraphUnit
import com.germani.dit.node.core.service.GraphDefinitionPreprocessor
import com.germani.dit.node.core.service.GraphProvider
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.concurrent.ConcurrentHashMap

@Service
class CacheableGraphDefinitionPreprocessor(
    private var nodeGraphProvider: GraphProvider
) : GraphDefinitionPreprocessor {

    //TODO: Make normal cache
    private val cache: MutableMap<String, GraphDefinition> = ConcurrentHashMap()

    override fun process(id: String): Mono<GraphDefinition> =
        if (cache.containsKey(id)) {
            Mono.just(cache[id]!!)
        } else {
            //TODO: validation should be here
            nodeGraphProvider.provide(id).map {
                val initNodeDefinition = mapToDefinition(it.initNode)
                val internalIdToNode = mutableMapOf<Long, NodeDefinition>()
                fillParents(internalIdToNode, initNodeDefinition, null)

                val terminatingNode = internalIdToNode.entries
                    .find { entry -> entry.value.isTerminating() }?.value
                    ?: throw MalformedGraphConfigurationException("Terminating node should be dined for the graph")

                GraphDefinition(id, initNodeDefinition, terminatingNode)
            }.doOnEach {
                if (it.hasValue()) {
                    cache[id] = it.get()!!
                } else throw InconsistentGraphStateException("Graph can not be found")
            }
        }


    private fun mapToDefinition(node: GraphUnit): NodeDefinition {
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