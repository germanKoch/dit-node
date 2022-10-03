package com.germani.dit.node.repo.api

import com.germani.dit.node.repo.db.model.ChainEntity
import com.germani.dit.node.repo.service.ChainService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/v1/chain/")
class ChainController(
    val service: ChainService
) {

    @GetMapping("/{id}")
    fun getNode(@PathVariable id: String): Mono<ChainEntity> {
        return service.getById(id)
    }
}