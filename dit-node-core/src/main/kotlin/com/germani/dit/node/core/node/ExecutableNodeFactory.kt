package com.germani.dit.node.core.node

import com.germani.dit.node.core.model.node.Node
import com.germani.dit.node.core.model.node.NodeType

interface ExecutableNodeFactory {

    fun getType(): NodeType

    fun getNode(node: Node): ExecutableNode

}