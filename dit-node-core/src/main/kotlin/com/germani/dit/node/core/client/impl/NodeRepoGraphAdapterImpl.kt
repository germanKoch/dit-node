package com.germani.dit.node.core.client.impl

import com.germani.dit.node.core.client.RepoGraphAdapter
import com.germani.dit.node.core.client.mapper.GraphMapper
import com.germani.dit.node.core.client.resource.GraphResource
import com.germani.dit.node.core.model.node.Graph
import feign.Headers
import feign.Param
import feign.RequestLine
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class NodeRepoGraphAdapterImpl(
    val client: NodeRepoGraphClient,
    val graphMapper: GraphMapper
) : RepoGraphAdapter {

    override fun getGraph(graphId: String): Mono<Graph> {
        return client.getGraph(graphId).map { graphMapper.map(it) }
    }

    @Headers("Accept: application/json")
    interface NodeRepoGraphClient {

        @RequestLine("GET /v1/graph/{id}")
        fun getGraph(@Param("id") graphId: String): Mono<GraphResource>

    }
}