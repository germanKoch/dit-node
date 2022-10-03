package com.germani.dit.node.core.service

import com.germani.dit.node.core.model.node.preprocessing.ChainDefinition
import reactor.core.publisher.Mono

interface ChainDefinitionPreprocessor {
    fun process(id: String): Mono<ChainDefinition>
}