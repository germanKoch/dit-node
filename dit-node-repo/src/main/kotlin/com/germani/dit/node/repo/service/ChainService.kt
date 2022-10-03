package com.germani.dit.node.repo.service

import com.germani.dit.node.repo.db.model.ChainEntity
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ChainService {
    fun getById(id: String): Mono<ChainEntity>

    fun getAll(): Flux<ChainEntity>
}