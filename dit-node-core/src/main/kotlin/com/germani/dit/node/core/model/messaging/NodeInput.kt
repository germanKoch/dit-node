package com.germani.dit.node.core.model.messaging

//TODO: make separate class for multiple
data class NodeInput(
    private val messages: List<com.germani.dit.node.core.model.messaging.Message>
) {
    fun getMessage(): com.germani.dit.node.core.model.messaging.Message {
        return messages.single()
    }

    fun getMessages(): List<com.germani.dit.node.core.model.messaging.Message> {
        return ArrayList(messages)
    }

    fun combine(node: com.germani.dit.node.core.model.messaging.NodeInput): com.germani.dit.node.core.model.messaging.NodeInput {
        return com.germani.dit.node.core.model.messaging.NodeInput(this.messages + node.messages)
    }
}