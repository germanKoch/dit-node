package com.germani.dit.node.core.model.messaging

class NodeParams private constructor(
    private val data: MutableMap<String, Any> = mutableMapOf()
) {

    operator fun get(key: String): Any? {
        return data[key]
    }

    operator fun set(key: String, value: Any) {
        data[key] = value
    }

    operator fun plus(context: NodeParams): NodeParams {
        return NodeParams(HashMap(this.data + context.data))
    }

    fun asMap(): Map<String, Any> {
        return HashMap(data)
    }

    fun merge(params: NodeParams): NodeParams {
        return NodeParams(data.apply {
            putAll(params.data)
        })
    }
    companion object {
        fun create(context: NodeParams): NodeParams {
            return NodeParams(HashMap(context.data))
        }

        fun merge(context1: NodeParams, context2: NodeParams): NodeParams {
            return NodeParams(HashMap(context1.data + context2.data))
        }
    }

}