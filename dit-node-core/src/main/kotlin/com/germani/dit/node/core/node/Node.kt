package com.germani.dit.node.core.node

import com.germani.dit.node.core.model.messaging.NodeInput
import com.germani.dit.node.core.model.messaging.NodeOutput
import reactor.core.publisher.Mono

interface Node {

    fun getNodeType(): NodeType

    fun process(input: Mono<NodeInput>): Mono<NodeOutput>

}