package com.germani.dit.node.core.service

import com.germani.dit.node.core.node.Node
import reactor.core.publisher.Mono

interface NodeProvider {

    fun getNode(id: String): Mono<Node>

}