package com.germani.dit.node.core.node.impl.transformation.processing

import com.fasterxml.jackson.databind.JsonNode
import com.germani.dit.node.core.model.node.transformation.TransformationScript

interface JsonScriptedTransformationService {

    fun transform(input: Map<String, JsonNode>, script: TransformationScript): JsonNode
}