package com.germani.dit.node.repo.service

import com.germani.ditnodecore.repo.db.model.ChainEntity
import reactor.core.publisher.Mono

interface ChainService {
    fun getById(id: String): Mono<ChainEntity>
}