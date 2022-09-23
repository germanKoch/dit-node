package com.germani.dit.node.core.node.impl

import com.germani.dit.node.core.model.messaging.NodeInput
import com.germani.dit.node.core.model.messaging.NodeOutput
import com.germani.dit.node.core.node.Node
import com.germani.dit.node.core.node.NodeType
import reactor.core.publisher.Mono

class IdentityNode : Node {

    override fun getNodeType(): NodeType {
        return NodeType.IDENTITY
    }

    override fun process(input: Mono<NodeInput>): Mono<NodeOutput> {
        println("" + getNodeType() + " Started at " + System.currentTimeMillis())
        var result = input.map {
            Thread.sleep(10000)
            NodeOutput(it.getMessage())
        }
        println("" + getNodeType() + "Finished at" + System.currentTimeMillis())
        return result
    }
}