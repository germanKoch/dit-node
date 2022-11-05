package com.germani.dit.node.core.model.messaging

import com.germani.dit.node.core.model.node.ContextableString
import org.springframework.http.HttpHeaders

class MessageHeaders(
    private val data: MutableMap<String, ContextableString>
) {

    fun asMap(context: Context): Map<String, String> {
        return HashMap(data).mapValues { it.value.get(context) }
    }

    fun toHttpHeaders(context: Context): HttpHeaders {
        return HttpHeaders().apply {
            asMap(context).forEach { (key, value) ->
                this.add(key, value)
            }
        }
    }

    fun merge(headers: MessageHeaders): MessageHeaders {
        return MessageHeaders(
            data.apply {
                putAll(headers.data)
            }
        )
    }

    companion object {
        fun fromHttpHeaders(httpHeaders: HttpHeaders): MessageHeaders {
            return MessageHeaders(
                httpHeaders.toSingleValueMap().mapValues { ContextableString(it.toString()) }.toMutableMap()
            )
        }
    }

}
