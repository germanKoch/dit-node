package com.germani.dit.node.core.node.impl.storage

import com.germani.dit.node.core.model.messaging.NodeInput
import com.germani.dit.node.core.model.messaging.NodeOutput
import com.germani.dit.node.core.node.Node
import com.germani.dit.node.core.node.NodeType
import reactor.core.publisher.Mono

class StorageNode(private val name: String) : Node {
    override fun getNodeType(): NodeType = NodeType.STORAGE

    override fun getNodeName(): String {
        return name
    }

    override fun process(input: Mono<NodeInput>): Mono<NodeOutput> {
        TODO("Not yet implemented")
    }
}