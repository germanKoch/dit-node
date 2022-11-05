package com.germani.dit.node.core.service

import com.germani.dit.node.core.node.ExecutableNode
import reactor.core.publisher.Mono

interface NodeProvider {

    fun getNode(id: String): Mono<ExecutableNode>

}