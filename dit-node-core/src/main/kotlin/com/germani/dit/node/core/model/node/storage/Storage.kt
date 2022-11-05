package com.germani.dit.node.core.model.node.storage

import com.germani.dit.node.core.model.node.ContextableString
import org.springframework.http.HttpMethod

data class Storage(
    val type: StorageType,
    val url: ContextableString,
    val method: HttpMethod
)