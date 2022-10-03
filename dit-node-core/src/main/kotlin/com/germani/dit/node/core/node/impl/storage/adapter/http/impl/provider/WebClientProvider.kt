package com.germani.dit.node.core.node.impl.storage.adapter.http.impl.provider

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class WebClientProvider(
    private val webClient: WebClient
) {

    fun getWebClient() = webClient

}