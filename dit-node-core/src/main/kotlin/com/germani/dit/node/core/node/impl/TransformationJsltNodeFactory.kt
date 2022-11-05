package com.germani.dit.node.core.node.impl

import com.germani.dit.node.core.model.node.Node
import com.germani.dit.node.core.model.node.NodeType
import com.germani.dit.node.core.node.ExecutableNode
import com.germani.dit.node.core.node.ExecutableNodeFactory
import com.germani.dit.node.core.node.impl.transformation.JsonBodyTransformationNode
import com.germani.dit.node.core.node.impl.transformation.processing.JsonScriptedTransformationService
import org.springframework.stereotype.Component

@Component
class TransformationJsltNodeFactory(
    val transformationService: JsonScriptedTransformationService
) : ExecutableNodeFactory {
    override fun getType(): NodeType = NodeType.JSON_TRANSFORMATION

    override fun getNode(node: Node): ExecutableNode {
        return JsonBodyTransformationNode(node, transformationService)
    }
}