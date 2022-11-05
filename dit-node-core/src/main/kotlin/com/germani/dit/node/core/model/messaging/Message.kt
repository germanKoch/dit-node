package com.germani.dit.node.core.model.messaging

import com.germani.dit.node.core.model.exception.InvalidDataFormatException
import com.germani.dit.node.core.model.node.ContextableString

data class Message(
    val data: Any,
    val headers: MessageHeaders,
    val context: Context,
    val params: NodeParams,
    val nodeSource: String
) {

    //TODO: error like this shoudl be log on the node level
    inline fun <reified T> data(): T {
        if (data is T) {
            return data
        }
        throw InvalidDataFormatException("Data can be parsed")
    }

}