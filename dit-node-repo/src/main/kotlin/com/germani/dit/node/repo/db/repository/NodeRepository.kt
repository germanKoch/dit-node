package com.germani.dit.node.repo.db.repository

import com.germani.dit.node.repo.db.model.NodeEntity
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface NodeRepository : ReactiveMongoRepository<NodeEntity, String> {
}