package com.germani.dit.node.core.client.resource

import com.germani.dit.node.core.model.node.storage.StorageType
import org.springframework.http.HttpMethod

data class StorageResource(
    val type: StorageType,
    val url: String,
    val method: HttpMethod
)
