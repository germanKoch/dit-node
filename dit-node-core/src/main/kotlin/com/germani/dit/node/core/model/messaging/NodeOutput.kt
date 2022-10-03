package com.germani.dit.node.core.model.messaging

class NodeOutput(
    private val message: Message
) {
    fun getMessage(): Message {
        return message
    }

    fun toInput(): NodeInput {
        return NodeInput(listOf(message))
    }
}