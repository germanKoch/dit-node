package com.germani.dit.node.repo.api

import com.germani.dit.node.repo.db.model.NodeEntity
import com.germani.dit.node.repo.service.NodeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

//TODO: сделать resource
@RestController
@RequestMapping("/v1/node/")
class NodeController(
    val service: NodeService
) {

    @GetMapping("/{id}")
    fun getNode(@PathVariable id: String): Mono<NodeEntity> {
        return service.getById(id)
    }

}