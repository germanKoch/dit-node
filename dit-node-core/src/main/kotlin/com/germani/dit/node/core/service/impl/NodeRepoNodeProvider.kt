package com.germani.dit.node.core.service.impl

import com.germani.dit.node.core.node.Node
import com.germani.dit.node.core.service.NodeProvider
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class NodeRepoNodeProvider : NodeProvider {

    override fun getNode(id: String): Mono<Node> {
        TODO("Not yet implemented")
    }
}