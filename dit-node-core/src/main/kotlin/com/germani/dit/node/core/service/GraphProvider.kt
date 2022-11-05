package com.germani.dit.node.core.service

import com.germani.dit.node.core.model.node.Graph
import reactor.core.publisher.Mono

interface GraphProvider {

    fun provide(id: String): Mono<Graph>

}