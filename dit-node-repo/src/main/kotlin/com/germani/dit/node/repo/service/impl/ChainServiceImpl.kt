package com.germani.dit.node.repo.service.impl

import com.germani.ditnodecore.repo.db.model.ChainEntity
import com.germani.ditnodecore.repo.db.repository.NodeChainRepository
import com.germani.ditnodecore.repo.service.ChainService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ChainServiceImpl(
    val chainRepository: NodeChainRepository
) : ChainService {

    override fun getById(id: String): Mono<ChainEntity> {
        return chainRepository.findById(id)
    }

}