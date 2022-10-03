package com.germani.dit.node.core.client.resource

data class ChainUnitResource(
    var internalId: Long,
    var nodeId: String,
    var childs: List<ChainUnitResource>
)