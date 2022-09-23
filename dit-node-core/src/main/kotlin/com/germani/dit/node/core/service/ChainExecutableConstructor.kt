package com.germani.dit.node.core.service

import com.germani.dit.node.core.node.impl.ExecutableChain
import com.germani.dit.node.core.model.preprocessing.ChainDefinition
import reactor.core.publisher.Mono

interface ChainExecutableConstructor {
    fun construct(definition: ChainDefinition): Mono<ExecutableChain>
}