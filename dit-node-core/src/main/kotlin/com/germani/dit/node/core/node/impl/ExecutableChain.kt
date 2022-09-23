package com.germani.dit.node.core.node.impl

import com.germani.dit.node.core.model.messaging.NodeInput
import com.germani.dit.node.core.model.messaging.NodeOutput
import reactor.core.publisher.Mono

//TODO: что сделать со стримингом
typealias ChainExecution = (com.germani.dit.node.core.model.messaging.NodeInput) -> Mono<NodeOutput>

class ExecutableChain(private val action: ChainExecution) {

    fun process(input: NodeInput): Mono<NodeOutput> {
        return action.invoke(input)
    }

}

