package com.germani.dit.node.core.service.impl

import com.germani.dit.node.core.model.messaging.NodeInput
import com.germani.dit.node.core.model.messaging.NodeOutput
import com.germani.dit.node.core.model.node.preprocessing.GraphDefinition
import com.germani.dit.node.core.model.node.preprocessing.NodeDefinition
import com.germani.dit.node.core.node.ExecutableGraph
import com.germani.dit.node.core.service.GraphExecutableConstructor
import com.germani.dit.node.core.service.NodeProvider
import reactor.core.publisher.Mono

class GraphExecutableConstructorImpl(
    private val nodeProvider: NodeProvider
) : GraphExecutableConstructor {

    private val internalIdToOutput: MutableMap<Long, Mono<NodeOutput>> = mutableMapOf()

    override fun construct(definition: GraphDefinition): Mono<ExecutableGraph> {
        val terminateNode = definition.terminatedNode
        return Mono.defer {
            Mono.just(
                ExecutableGraph {
                    recursivelyConstructInternal(it, terminateNode)
                }
            )
        }
    }

    private fun recursivelyConstructInternal(
        input: NodeInput,
        definition: NodeDefinition
    ): Mono<NodeOutput> {
        if (definition.isInitial()) {
            return nodeProvider.getNode(definition.nodeId).flatMap { it.execute(Mono.just(input)) }
        }

        val parents = definition.parents
        parents.forEach {
            if (!internalIdToOutput.containsKey(it.internalId)) {
                internalIdToOutput[it.internalId] = recursivelyConstructInternal(input, it)
            }
        }

        return if (parents.size > 1) {
            nodeProvider.getNode(definition.nodeId).flatMap { node ->
                node.execute(
                    Mono.zip(parents.map { internalIdToOutput[it.internalId]!!.map { output -> output.toInput() } }) {
                        it.map { element -> element as NodeInput }.reduce { acc, input -> acc.combine(input) }
                    })
            }
        } else {
            nodeProvider.getNode(definition.nodeId).flatMap { node ->
                node.execute(internalIdToOutput[parents.single().internalId]!!.map { it.toInput() })
            }
        }
    }
}