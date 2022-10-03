package com.germani.dit.node.core.service

import com.germani.dit.node.core.model.node.preprocessing.ChainDefinition
import com.germani.dit.node.core.node.ExecutableChain
import reactor.core.publisher.Mono

interface ChainExecutableConstructor {
    fun construct(definition: ChainDefinition): Mono<ExecutableChain>
}