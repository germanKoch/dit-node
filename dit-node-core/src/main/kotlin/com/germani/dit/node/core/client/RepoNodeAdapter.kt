package com.germani.dit.node.core.client

import com.germani.dit.node.core.model.node.Node
import reactor.core.publisher.Mono

interface RepoNodeAdapter {
    fun getNode(nodeId: String): Mono<Node>
}