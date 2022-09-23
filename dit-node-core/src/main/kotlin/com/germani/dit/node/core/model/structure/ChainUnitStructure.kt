package com.germani.dit.node.core.model.structure

class ChainUnitStructure(
    var internalId: Long,
    var nodeId: String,
    var childs: List<ChainUnitStructure>
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChainUnitStructure

        if (internalId != other.internalId) return false
        if (nodeId != other.nodeId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = internalId.hashCode()
        result = 31 * result + nodeId.hashCode()
        return result
    }

    override fun toString(): String {
        return "Node(internalId=$internalId, nodeId=$nodeId)"
    }
}