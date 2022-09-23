package com.germani.dit.node.core.node.impl

import com.germani.dit.node.core.model.messaging.NodeInput
import com.germani.dit.node.core.model.messaging.NodeOutput
import com.germani.dit.node.core.node.Node
import com.germani.dit.node.core.node.NodeType
import reactor.core.publisher.Mono

class ReverseNode : Node {
    override fun getNodeType(): NodeType = NodeType.REVERSE

    override fun process(input: Mono<NodeInput>): Mono<NodeOutput> {
        println("" + getNodeType() + " Started at " + System.currentTimeMillis())
        var result = input.map {
            Thread.sleep(10000)
            NodeOutput(com.germani.dit.node.core.model.messaging.Message(it.getMessage().data.reversed()))
        }
        println("" + getNodeType() + "Finished at" + System.currentTimeMillis())
        return result
    }
}