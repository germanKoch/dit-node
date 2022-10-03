package com.germani.dit.node.core.node.impl.transformation

import com.fasterxml.jackson.databind.JsonNode
import com.germani.dit.node.core.model.messaging.Context
import com.germani.dit.node.core.model.messaging.Message
import com.germani.dit.node.core.model.messaging.NodeInput
import com.germani.dit.node.core.model.messaging.NodeOutput
import com.germani.dit.node.core.model.node.transformation.TransformationScript
import com.germani.dit.node.core.node.Node
import com.germani.dit.node.core.node.NodeType
import com.germani.dit.node.core.node.impl.transformation.processing.JsonScriptedTransformationService
import reactor.core.publisher.Mono

class JsonBodyTransformationNode(
    private val name: String,
    private val jsltScript: String,
    private val transformationService: JsonScriptedTransformationService
) : Node {

    override fun getNodeType(): NodeType {
        return NodeType.JSON_TRANSFORMATION
    }

    override fun getNodeName(): String {
        return name
    }

    override fun process(input: Mono<NodeInput>): Mono<NodeOutput> {
        return input.map { nodeInput ->
            nodeInput.getMessages().let { messages ->
                messages.associate { it.nodeSource to it.data<JsonNode>() }.let {
                    transformationService.transform(it, TransformationScript(jsltScript))
                }.let { transformationResult ->
                    val headers = messages.map { HashMap(it.headers) }.reduce { acc, map ->
                        acc.apply { putAll(map) }
                    }.toMap()
                    val context = messages.map { it.context }.reduce { acc, context -> acc + context }
                    NodeOutput(
                        Message(transformationResult, headers, context, getNodeName())
                    )
                }
            }
        }

    }
}