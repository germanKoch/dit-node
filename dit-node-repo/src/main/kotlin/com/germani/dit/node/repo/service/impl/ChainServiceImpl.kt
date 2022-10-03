package com.germani.dit.node.repo.service.impl

import com.germani.dit.node.repo.db.model.ChainEntity
import com.germani.dit.node.repo.db.repository.ChainRepository
import com.germani.dit.node.repo.service.ChainService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ChainServiceImpl(
    val chainRepository: ChainRepository
) : ChainService {

    override fun getById(id: String): Mono<ChainEntity> {
        return chainRepository.findById(id)
    }

}