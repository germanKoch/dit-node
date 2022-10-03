package com.germani.dit.node.core.model.messaging

class Context private constructor(
    private val data: MutableMap<String, Any> = mutableMapOf()
) {

    operator fun get(key: String): Any? {
        return data[key]
    }

    operator fun set(key: String, value: Any) {
        data[key] = value
    }

    operator fun plus(context: Context): Context {
        return Context(HashMap(this.data + context.data))
    }

    companion object {
        fun create(context: Context): Context {
            return Context(HashMap(context.data))
        }

        fun merge(context1: Context, context2: Context): Context {
            return Context(HashMap(context1.data + context2.data))
        }
    }

}