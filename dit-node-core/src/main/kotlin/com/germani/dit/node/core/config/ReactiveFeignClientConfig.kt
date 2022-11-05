package com.germani.dit.node.core.config

import com.germani.dit.node.core.client.impl.NodeRepoGraphAdapterImpl
import com.germani.dit.node.core.client.impl.RepoNodeAdapterImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactivefeign.webclient.WebReactiveFeign

@Configuration
class ReactiveFeignClientConfig {

    @Bean
    fun gerGraphClient(): NodeRepoGraphAdapterImpl.NodeRepoGraphClient {
        return WebReactiveFeign.builder<NodeRepoGraphAdapterImpl.NodeRepoGraphClient>()
            .target(NodeRepoGraphAdapterImpl.NodeRepoGraphClient::class.java, "http://localhost:8081")
    }

    @Bean
    fun getNodeClient(): RepoNodeAdapterImpl.NodeRepoNodeClient {
        return WebReactiveFeign.builder<RepoNodeAdapterImpl.NodeRepoNodeClient>()
            .target(RepoNodeAdapterImpl.NodeRepoNodeClient::class.java, "http://localhost:8081")
    }

}