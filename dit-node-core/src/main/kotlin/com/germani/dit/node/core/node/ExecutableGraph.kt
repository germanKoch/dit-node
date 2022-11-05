package com.germani.dit.node.core.node

import com.germani.dit.node.core.model.messaging.NodeInput
import com.germani.dit.node.core.model.messaging.NodeOutput
import reactor.core.publisher.Mono

//TODO: что сделать со стримингом
typealias GraphExecution = (NodeInput) -> Mono<NodeOutput>

class ExecutableGraph(private val action: GraphExecution) {

    fun process(input: NodeInput): Mono<NodeOutput> {
        return action.invoke(input)
    }

}

