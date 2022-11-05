package com.germani.dit.node.core.service

import com.germani.dit.node.core.model.node.preprocessing.GraphDefinition
import reactor.core.publisher.Mono

interface GraphDefinitionPreprocessor {
    fun process(id: String): Mono<GraphDefinition>
}