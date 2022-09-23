package com.germani.dit.node.core.model.preprocessing

data class NodeDefinition(
    val internalId: Long,
    val nodeId: String,
    val childs: MutableList<NodeDefinition> = mutableListOf(),
    val parents: MutableList<NodeDefinition> = mutableListOf(),
) {
    fun isTerminating() = childs.isEmpty()

    fun isInitial() = parents.isEmpty()
}