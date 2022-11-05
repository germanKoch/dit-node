package com.germani.dit.node.core.node.impl.storage

import com.germani.dit.node.core.model.exception.InconsistentGraphStateException
import com.germani.dit.node.core.model.messaging.Message
import com.germani.dit.node.core.model.messaging.MessageHeaders
import com.germani.dit.node.core.model.messaging.NodeInput
import com.germani.dit.node.core.model.messaging.NodeOutput
import com.germani.dit.node.core.model.node.Node
import com.germani.dit.node.core.model.node.NodeType
import com.germani.dit.node.core.model.node.storage.http.HttpRequest
import com.germani.dit.node.core.node.ExecutableNode
import com.germani.dit.node.core.node.impl.storage.adapter.http.impl.HttpWebClientAdapter
import reactor.core.publisher.Mono

class StorageNode(val node: Node, private val adapter: HttpWebClientAdapter) : ExecutableNode {
    override fun getNodeType(): NodeType = NodeType.STORAGE

    override fun getNodeName(): String {
        return node.name
    }

    override fun execute(input: Mono<NodeInput>): Mono<NodeOutput> {
        val storage = node.storage ?: throw InconsistentGraphStateException("storage should not be null")
        return input.flatMap {
            val context = it.getMessage().context
            val message = it.getMessage()

            val request = HttpRequest.create(
                url = storage.url.get(context),
                headers = message.headers.toHttpHeaders(context),
                method = storage.method,
                body = message.data,
                requestParams = message.params.asMap()
            )
            adapter.exchange(request).map { response ->
                NodeOutput(
                    Message(
                        response.body,
                        MessageHeaders.fromHttpHeaders(response.headers),
                        context,
                        message.params,
                        node.name
                    )
                )
            }
        }
    }
}