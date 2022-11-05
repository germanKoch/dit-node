package com.germani.dit.node.core.client

import com.germani.dit.node.core.model.node.Graph
import reactor.core.publisher.Mono

interface RepoGraphAdapter {
    fun getGraph(graphId: String): Mono<Graph>
}