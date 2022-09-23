package com.germani.dit.node.core.node.impl

import com.germani.dit.node.core.model.messaging.Message
import com.germani.dit.node.core.model.messaging.NodeInput
import com.germani.dit.node.core.model.messaging.NodeOutput
import com.germani.dit.node.core.node.Node
import com.germani.dit.node.core.node.NodeType
import reactor.core.publisher.Mono

class UpperNode: Node {
    override fun getNodeType(): NodeType = NodeType.UPPER

    override fun process(input: Mono<com.germani.dit.node.core.model.messaging.NodeInput>): Mono<NodeOutput> {
        println("" + getNodeType() + " Started at " + System.currentTimeMillis())
        var result = input.map {
            Thread.sleep(10000)
            NodeOutput(com.germani.dit.node.core.model.messaging.Message(it.getMessage().data.uppercase()))
        }
        println("" + getNodeType() + "Finished at" + System.currentTimeMillis())
        return result
    }
}