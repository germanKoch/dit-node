package com.germani.dit.node.core.node.impl.storage.adapter.http.impl

import com.germani.dit.node.core.model.node.storage.http.HttpRequest
import com.germani.dit.node.core.model.node.storage.http.HttpResponse
import com.germani.dit.node.core.node.impl.storage.adapter.http.HttpAdapter
import com.germani.dit.node.core.node.impl.storage.adapter.http.impl.provider.WebClientProvider
import org.springframework.http.ReactiveHttpOutputMessage
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters
import reactor.core.publisher.Mono

@Component
class HttpWebClientAdapter(
    private val webClientProvider: WebClientProvider
) : HttpAdapter {

    override fun <T> exchange(request: HttpRequest<T>): Mono<HttpResponse<T>> {
        return webClientProvider.getWebClient()
            .method(request.method)
            .uri(request.url)
            .headers {
                it.addAll(request.headers)
            }.body(getBodyInserter(request))
            .exchangeToMono { response ->
                response.bodyToMono(request.bodyClass).map {
                    HttpResponse(
                        status = response.statusCode(),
                        headers = response.headers().asHttpHeaders(),
                        body = it
                    )
                }
            }
    }

    private fun <T> getBodyInserter(request: HttpRequest<T>): BodyInserter<T, ReactiveHttpOutputMessage> {
        return if (request.body != null) {
            BodyInserters.fromValue(request.body)
        } else {
            BodyInserters.empty()
        }
    }

}