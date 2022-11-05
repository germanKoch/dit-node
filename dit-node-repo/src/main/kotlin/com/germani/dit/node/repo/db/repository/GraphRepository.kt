package com.germani.dit.node.repo.db.repository

import com.germani.dit.node.repo.db.model.GraphEntity
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface GraphRepository : ReactiveMongoRepository<GraphEntity, String> {
}