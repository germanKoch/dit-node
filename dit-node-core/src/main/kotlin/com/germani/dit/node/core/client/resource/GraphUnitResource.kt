package com.germani.dit.node.core.client.resource

data class GraphUnitResource(
    var internalId: Long,
    var nodeId: String,
    var childs: List<GraphUnitResource>
)