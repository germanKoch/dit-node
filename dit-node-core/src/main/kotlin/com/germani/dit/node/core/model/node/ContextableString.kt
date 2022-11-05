package com.germani.dit.node.core.model.node

import com.germani.dit.node.core.model.messaging.Context

class ContextableString(
    private val strWithVars: String
) {

    fun get(context: Context): String {
        return injectVars(context, strWithVars)
    }

    private fun injectVars(context: Context, stringWithVars: String): String {
        var result = stringWithVars;
        context.getData().forEach { (key, value) ->
            result = result.replace("@$key", value.toString())
        }
        return result
    }

}