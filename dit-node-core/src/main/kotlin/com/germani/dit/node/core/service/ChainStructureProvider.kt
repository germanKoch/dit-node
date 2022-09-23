package com.germani.dit.node.core.service

import com.germani.dit.node.core.model.structure.ChainStructure
import reactor.core.publisher.Mono

interface ChainStructureProvider {

    fun provide(id: String): Mono<ChainStructure>

}