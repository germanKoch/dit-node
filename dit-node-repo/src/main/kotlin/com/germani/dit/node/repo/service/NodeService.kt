package com.germani.dit.node.repo.service

import com.germani.dit.node.repo.db.model.NodeEntity
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface NodeService {

    fun getById(id: String): Mono<NodeEntity>

    fun getAll(): Flux<NodeEntity>

}
