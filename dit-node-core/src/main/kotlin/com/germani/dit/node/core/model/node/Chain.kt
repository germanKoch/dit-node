package com.germani.dit.node.core.model.node

class Chain(
    val id: String? = null,
    val initNode: ChainUnit
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Chain

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
