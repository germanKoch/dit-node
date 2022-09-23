package com.germani.dit.node.repo.db.repository

import com.germani.ditnodecore.repo.db.model.ChainEntity
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface NodeChainRepository : ReactiveMongoRepository<ChainEntity, String> {
}