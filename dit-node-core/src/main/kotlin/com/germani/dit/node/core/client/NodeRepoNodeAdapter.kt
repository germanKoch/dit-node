package com.germani.dit.node.core.client

import com.germani.dit.node.core.client.resource.NodeResource
import feign.Headers
import feign.Param
import feign.RequestLine
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class NodeRepoNodeAdapter(
    val client: NodeRepoNodeClient
) {


    @Headers("Accept: application/json")
    interface NodeRepoNodeClient {

        @RequestLine("GET /v1/node/{id}")
        fun getAvailableFlavors(@Param("id") chainId: String): Mono<NodeResource>

    }
}