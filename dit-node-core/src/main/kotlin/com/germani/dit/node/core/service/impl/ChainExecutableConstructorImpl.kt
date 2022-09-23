package com.germani.dit.node.core.service.impl

import com.germani.dit.node.core.model.messaging.NodeInput
import com.germani.dit.node.core.model.messaging.NodeOutput
import com.germani.dit.node.core.node.impl.ExecutableChain
import com.germani.dit.node.core.model.preprocessing.ChainDefinition
import com.germani.dit.node.core.model.preprocessing.NodeDefinition
import com.germani.dit.node.core.service.ChainExecutableConstructor
import com.germani.dit.node.core.service.NodeProvider
import reactor.core.publisher.Mono

class ChainExecutableConstructorImpl(
    private val nodeProvider: NodeProvider
) : ChainExecutableConstructor {

    private val internalIdToOutput: MutableMap<Long, Mono<NodeOutput>> = mutableMapOf()

    override fun construct(definition: ChainDefinition): Mono<ExecutableChain> {
        val terminateNode = definition.terminatedNode
        return Mono.defer {
            Mono.just(
                ExecutableChain {
                    recursivelyConstructInternal(it, terminateNode)
                }
            )
        }
    }

    private fun recursivelyConstructInternal(
        input: com.germani.dit.node.core.model.messaging.NodeInput,
        definition: NodeDefinition
    ): Mono<NodeOutput> {
        if (definition.isInitial()) {
            return nodeProvider.getNode(definition.nodeId).flatMap { it.process(Mono.just(input)) }
        }

        val parents = definition.parents
        parents.forEach {
            if (!internalIdToOutput.containsKey(it.internalId)) {
                internalIdToOutput[it.internalId] = recursivelyConstructInternal(input, it)
            }
        }

        if (parents.size > 1) {
            return nodeProvider.getNode(definition.nodeId).flatMap { node ->
                node.process(Mono.zip(parents.map { internalIdToOutput[it.internalId]!!.map { output -> output.toInput() } }) {
                    return@zip it.map { element -> element as com.germani.dit.node.core.model.messaging.NodeInput }
                        .reduce { acc, input -> acc.combine(input) }
                })
            }
        } else {
            return nodeProvider.getNode(definition.nodeId).flatMap { node ->
                node.process(internalIdToOutput[parents.single().internalId]!!.map {
                    it.toInput()
                })
            }
        }
    }
}