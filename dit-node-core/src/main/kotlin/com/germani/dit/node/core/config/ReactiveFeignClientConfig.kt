package com.germani.dit.node.core.config

import com.germani.dit.node.core.client.NodeRepoChainAdapter
import com.germani.dit.node.core.client.NodeRepoNodeAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactivefeign.webclient.WebReactiveFeign

@Configuration
class ReactiveFeignClientConfig {

    @Bean
    fun getChainClient(): NodeRepoChainAdapter.NodeRepoChainClient {
        return WebReactiveFeign.builder<NodeRepoChainAdapter.NodeRepoChainClient>()
            .target(NodeRepoChainAdapter.NodeRepoChainClient::class.java, "http://localhost:8081")
    }

    @Bean
    fun getNodeClient(): NodeRepoNodeAdapter.NodeRepoNodeClient {
        return WebReactiveFeign.builder<NodeRepoNodeAdapter.NodeRepoNodeClient>()
            .target(NodeRepoNodeAdapter.NodeRepoNodeClient::class.java, "http://localhost:8081")
    }

}