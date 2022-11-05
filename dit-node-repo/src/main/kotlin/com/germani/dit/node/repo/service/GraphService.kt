package com.germani.dit.node.repo.service

import com.germani.dit.node.repo.db.model.GraphEntity
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface GraphService {
    fun getById(id: String): Mono<GraphEntity>

    fun getAll(): Flux<GraphEntity>
}