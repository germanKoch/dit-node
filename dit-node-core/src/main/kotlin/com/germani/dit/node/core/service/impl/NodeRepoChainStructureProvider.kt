package com.germani.dit.node.core.service.impl

import com.germani.dit.node.core.model.structure.ChainStructure
import com.germani.dit.node.core.model.structure.ChainUnitStructure
import com.germani.dit.node.core.service.ChainStructureProvider
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class NodeRepoChainStructureProvider : ChainStructureProvider {

    override fun provide(id: String): Mono<ChainStructure> {
        TODO("Not yet implemented")
    }

}