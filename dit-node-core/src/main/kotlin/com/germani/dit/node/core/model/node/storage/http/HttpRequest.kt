package com.germani.dit.node.core.model.node.storage.http

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod

data class HttpRequest<T>(
    val url: String,
    val headers: HttpHeaders,
    val method: HttpMethod,
    val bodyClass: Class<out T>,
    val body: T
) {

    companion object {
        fun <T: Any> create(url: String, headers: HttpHeaders, method: HttpMethod, body: T): HttpRequest<T> {
            return HttpRequest(url, headers, method, body::class.java, body)
        }
    }
}