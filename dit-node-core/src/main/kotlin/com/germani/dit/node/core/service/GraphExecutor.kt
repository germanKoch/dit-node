package com.germani.dit.node.core.service

import com.germani.dit.node.core.model.messaging.NodeInput
import com.germani.dit.node.core.model.messaging.NodeOutput
import reactor.core.publisher.Mono

interface GraphExecutor {

    fun execute(graphId: String, input: NodeInput): Mono<NodeOutput>

}