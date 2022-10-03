package com.germani.dit.node.repo.service.impl

import com.germani.dit.node.repo.db.model.NodeEntity
import com.germani.dit.node.repo.db.repository.NodeRepository
import com.germani.dit.node.repo.service.NodeService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class NodeServiceImpl(
    val nodeRepository: NodeRepository
) : NodeService {
    override fun getById(id: String): Mono<NodeEntity> {
        return nodeRepository.findById(id)
    }

    override fun getAll(): Flux<NodeEntity> {
        return nodeRepository.findAll()
    }
}