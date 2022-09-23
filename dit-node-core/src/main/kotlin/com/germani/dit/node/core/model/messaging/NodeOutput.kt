package com.germani.dit.node.core.model.messaging

class NodeOutput(
    private val message: com.germani.dit.node.core.model.messaging.Message
) {
    fun getMessage(): com.germani.dit.node.core.model.messaging.Message {
        return message
    }

    fun toInput(): com.germani.dit.node.core.model.messaging.NodeInput {
        return com.germani.dit.node.core.model.messaging.NodeInput(listOf(message))
    }
}