package com.germani.dit.node.core.node.impl.transformation.processing.impl

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.NullNode
import com.germani.dit.node.core.model.node.transformation.TransformationScript
import com.germani.dit.node.core.node.impl.transformation.processing.JsonScriptedTransformationService
import com.schibsted.spt.data.jslt.Parser
import org.springframework.stereotype.Service

@Service
class JsltJsonScriptedTransformationService : JsonScriptedTransformationService {

    override fun transform(input: Map<String, JsonNode>, script: TransformationScript): JsonNode {
        val (scriptBody) = script

        val compiledString = Parser.compileString(scriptBody)
        return compiledString.apply(input, NullNode.instance)
    }


}