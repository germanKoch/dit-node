package com.germani.dit.node.core.model.node

import com.germani.dit.node.core.model.node.storage.Storage
import com.germani.dit.node.core.model.node.transformation.TransformationScript

data class Node(
    val id: String,
    val name: String,
    val nodeType: NodeType,
    val storage: Storage? = null,
    val transformation: TransformationScript? = null
)