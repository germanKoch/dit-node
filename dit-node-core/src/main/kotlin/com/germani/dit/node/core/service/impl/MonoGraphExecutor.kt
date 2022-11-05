package com.germani.dit.node.core.service.impl

import com.germani.dit.node.core.model.messaging.NodeInput
import com.germani.dit.node.core.model.messaging.NodeOutput
import com.germani.dit.node.core.service.GraphDefinitionPreprocessor
import com.germani.dit.node.core.service.GraphExecutableConstructorFactory
import com.germani.dit.node.core.service.GraphExecutor
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class MonoGraphExecutor(
    private val graphDefinitionPreprocessor: GraphDefinitionPreprocessor,
    private val graphExecutableConstructorFactory: GraphExecutableConstructorFactory
) : GraphExecutor {

    override fun execute(graphId: String, input: NodeInput): Mono<NodeOutput> {
        return graphDefinitionPreprocessor.process(graphId).flatMap {
            graphExecutableConstructorFactory.getConstructor().construct(it)
        }.flatMap { it.process(input) }
    }

}