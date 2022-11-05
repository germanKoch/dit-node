package com.germani.dit.node.core.node.impl

import com.germani.dit.node.core.model.node.Node
import com.germani.dit.node.core.model.node.NodeType
import com.germani.dit.node.core.node.ExecutableNode
import com.germani.dit.node.core.node.ExecutableNodeFactory
import com.germani.dit.node.core.node.impl.storage.StorageNode
import com.germani.dit.node.core.node.impl.storage.adapter.http.impl.HttpWebClientAdapter
import org.springframework.stereotype.Component

@Component
class StorageNodeFactory(
    val adapter: HttpWebClientAdapter
) : ExecutableNodeFactory {
    override fun getType(): NodeType = NodeType.STORAGE

    override fun getNode(node: Node): ExecutableNode {
        return StorageNode(node, adapter)
    }

}