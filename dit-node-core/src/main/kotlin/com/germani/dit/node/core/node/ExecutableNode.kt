package com.germani.dit.node.core.node

import com.germani.dit.node.core.model.messaging.NodeInput
import com.germani.dit.node.core.model.messaging.NodeOutput
import com.germani.dit.node.core.model.node.NodeType
import reactor.core.publisher.Mono

interface ExecutableNode {

    fun execute(input: Mono<NodeInput>): Mono<NodeOutput>

    fun getNodeType(): NodeType

    fun getNodeName(): String

}