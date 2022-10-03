package com.germani.dit.node.core.node.impl.storage.adapter.http

import com.germani.dit.node.core.model.node.storage.http.HttpRequest
import com.germani.dit.node.core.model.node.storage.http.HttpResponse
import reactor.core.publisher.Mono

interface HttpAdapter {

    fun <T> exchange(request: HttpRequest<T>): Mono<HttpResponse<T>>

}