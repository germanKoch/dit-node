package com.germani.dit.node.core.model.messaging

import com.germani.dit.node.core.model.exception.InvalidDataFormatException

data class Message(
    val data: Any,
    val headers: Map<String, String>,
    val context: Context,
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