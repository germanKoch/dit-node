package com.germani.dit.node.core.service.impl

import com.germani.dit.node.core.service.GraphExecutableConstructor
import com.germani.dit.node.core.service.GraphExecutableConstructorFactory
import org.springframework.stereotype.Service

@Service
class GraphExecutableConstructorFactoryImpl(
    val nodeProvider: CacheableNodeRepoNodeProvider
) : GraphExecutableConstructorFactory {

    override fun getConstructor(): GraphExecutableConstructor {
        return GraphExecutableConstructorImpl(nodeProvider)
    }
}