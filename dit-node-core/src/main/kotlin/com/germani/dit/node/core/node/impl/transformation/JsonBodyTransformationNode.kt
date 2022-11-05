package com.germani.dit.node.core.node.impl.transformation

import com.fasterxml.jackson.databind.JsonNode
import com.germani.dit.node.core.model.messaging.Message
import com.germani.dit.node.core.model.messaging.NodeInput
import com.germani.dit.node.core.model.messaging.NodeOutput
import com.germani.dit.node.core.model.node.Node
import com.germani.dit.node.core.model.node.NodeType
import com.germani.dit.node.core.model.node.transformation.TransformationScript
import com.germani.dit.node.core.node.ExecutableNode
import com.germani.dit.node.core.node.impl.transformation.processing.JsonScriptedTransformationService
import reactor.core.publisher.Mono

class JsonBodyTransformationNode(
    private val node: Node,
    private val transformationService: JsonScriptedTransformationService
) : ExecutableNode {

    override fun getNodeType(): NodeType {
        return NodeType.JSON_TRANSFORMATION
    }

    override fun getNodeName(): String {
        return node.name
    }

    override fun execute(input: Mono<NodeInput>): Mono<NodeOutput> {
        return input.map { nodeInput ->
            nodeInput.getMessages().let { messages ->
                messages.associate { it.nodeSource to it.data<JsonNode>() }.let {
                    transformationService.transform(it, node.transformation!!)
                }.let { transformationResult ->
                    val headers = messages.map { it.headers }.reduce { acc, headers -> acc.merge(headers) }
                    val params = messages.map { it.params }.reduce { acc, nodeParams -> acc.merge(nodeParams) }
                    val context = messages.map { it.context }.reduce { acc, context -> acc + context }
                    NodeOutput(
                        Message(
                            transformationResult,
                            headers,
                            context,
                            params,
                            getNodeName()
                        )
                    )
                }
            }
        }

    }
}