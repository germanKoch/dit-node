package com.germani.dit.node.core.model.structure

class ChainStructure(
    var id: String,
    var initNode: ChainUnitStructure
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChainStructure

        if (initNode != other.initNode) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = initNode.hashCode()
        result = 31 * result + (id?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "NodeChain(nodes=$initNode, id=$id)"
    }
}