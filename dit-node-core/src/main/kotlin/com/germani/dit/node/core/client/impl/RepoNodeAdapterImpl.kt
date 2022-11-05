package com.germani.dit.node.core.client.impl

import com.germani.dit.node.core.client.RepoNodeAdapter
import com.germani.dit.node.core.client.mapper.NodeMapper
import com.germani.dit.node.core.client.resource.NodeResource
import com.germani.dit.node.core.model.node.Node
import feign.Headers
import feign.Param
import feign.RequestLine
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class RepoNodeAdapterImpl(
    val client: NodeRepoNodeClient,
    val nodeMapper: NodeMapper
): RepoNodeAdapter {

    override fun getNode(nodeId: String): Mono<Node> {
        return client.getNode(nodeId).map { nodeMapper.map(it) }
    }

    @Headers("Accept: application/json")
    interface NodeRepoNodeClient {

        @RequestLine("GET /v1/node/{id}")
        fun getNode(@Param("id") graphId: String): Mono<NodeResource>

    }
}