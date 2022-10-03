package com.germani.dit.node.repo.db.repository

import com.germani.dit.node.repo.db.model.ChainEntity
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ChainRepository : ReactiveMongoRepository<ChainEntity, String> {
}