package com.germani.dit.node.repo.service.impl

import com.germani.dit.node.repo.db.model.GraphEntity
import com.germani.dit.node.repo.db.repository.GraphRepository
import com.germani.dit.node.repo.service.GraphService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class GraphServiceImpl(
    val graphRepository: GraphRepository
) : GraphService {

    override fun getById(id: String): Mono<GraphEntity> {
        return graphRepository.findById(id)
    }

    override fun getAll(): Flux<GraphEntity> {
        return graphRepository.findAll()
    }

}