package com.germani.dit.node.core.model.node.storage.http

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus

data class HttpResponse<T>(
    val status: HttpStatus,
    val headers: HttpHeaders,
    val body: T
)