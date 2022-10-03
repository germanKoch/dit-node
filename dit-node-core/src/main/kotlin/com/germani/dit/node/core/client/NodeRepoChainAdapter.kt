package com.germani.dit.node.core.client

import com.germani.dit.node.core.client.resource.ChainResource
import feign.Headers
import feign.Param
import feign.RequestLine
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class NodeRepoChainAdapter(
    val client: NodeRepoChainClient
) {



    @Headers("Accept: application/json")
    interface NodeRepoChainClient {

        @RequestLine("GET /v1/chain/{id}")
        fun getAvailableFlavors(@Param("id") chainId: String): Mono<ChainResource>

    }
}