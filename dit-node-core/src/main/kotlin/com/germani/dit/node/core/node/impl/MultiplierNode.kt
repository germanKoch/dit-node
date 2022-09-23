package com.germani.dit.node.core.node.impl

import com.germani.dit.node.core.model.messaging.Message
import com.germani.dit.node.core.model.messaging.NodeInput
import com.germani.dit.node.core.model.messaging.NodeOutput
import com.germani.dit.node.core.node.Node
import com.germani.dit.node.core.node.NodeType
import reactor.core.publisher.Mono

class MultiplierNode : Node {

    override fun getNodeType(): NodeType {
        return NodeType.CONCAT
    }

    override fun process(input: Mono<NodeInput>): Mono<NodeOutput> {
        println("" + getNodeType() + " Started at " + System.currentTimeMillis())
        var result = input.map {
            Thread.sleep(1000)
            NodeOutput(
                Message(
                    (it.getMessages() + it.getMessages()).joinToString(
                        separator = "_"
                    ) { it.data })
            )
        }
        println("" + getNodeType() + "Finished at" + System.currentTimeMillis())
        return result
    }

}