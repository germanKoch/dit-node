package com.germani.dit.node.core.client.resource

import com.germani.dit.node.core.model.node.NodeType

data class NodeResource(
    var id: String,
    val name: String,
    val nodeType: NodeType,
    val storage: StorageResource? = null,
)