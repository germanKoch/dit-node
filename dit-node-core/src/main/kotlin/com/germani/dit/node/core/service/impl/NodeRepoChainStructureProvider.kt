package com.germani.dit.node.core.service.impl

import com.germani.dit.node.core.model.node.Chain
import com.germani.dit.node.core.service.ChainStructureProvider
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class NodeRepoChainStructureProvider : ChainStructureProvider {

    override fun provide(id: String): Mono<Chain> {
        TODO("Not yet implemented")
    }

}