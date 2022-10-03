package com.germani.dit.node.core.model.node.preprocessing

data class ChainDefinition(
    var id: String,
    var initNode: NodeDefinition,
    var terminatedNode: NodeDefinition
)