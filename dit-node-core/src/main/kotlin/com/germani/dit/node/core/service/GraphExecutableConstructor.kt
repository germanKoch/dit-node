package com.germani.dit.node.core.service

import com.germani.dit.node.core.model.node.preprocessing.GraphDefinition
import com.germani.dit.node.core.node.ExecutableGraph
import reactor.core.publisher.Mono

interface GraphExecutableConstructor {
    fun construct(definition: GraphDefinition): Mono<ExecutableGraph>
}