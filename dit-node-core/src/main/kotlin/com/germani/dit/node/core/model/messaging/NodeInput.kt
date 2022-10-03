package com.germani.dit.node.core.model.messaging

//TODO: make separate class for multiple
data class NodeInput(
    private val messages: List<Message>,
) {
    fun getMessage(): Message {
        return messages.single()
    }

    fun getMessages(): List<Message> {
        return ArrayList(messages)
    }

    fun combine(node: NodeInput): NodeInput {
        return NodeInput(this.messages + node.messages)
    }
}