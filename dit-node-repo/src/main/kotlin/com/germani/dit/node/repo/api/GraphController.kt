package com.germani.dit.node.repo.api

import com.germani.dit.node.repo.db.model.GraphEntity
import com.germani.dit.node.repo.service.GraphService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/v1/graph/")
class GraphController(
    val service: GraphService
) {

    @GetMapping("/{id}")
    fun getNode(@PathVariable id: String): Mono<GraphEntity> {
        return service.getById(id)
    }
}