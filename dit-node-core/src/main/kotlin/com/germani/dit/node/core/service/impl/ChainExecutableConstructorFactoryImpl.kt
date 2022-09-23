package com.germani.dit.node.core.service.impl

import com.germani.dit.node.core.service.ChainExecutableConstructor
import com.germani.dit.node.core.service.ChainExecutableConstructorFactory
import org.springframework.stereotype.Service

@Service
class ChainExecutableConstructorFactoryImpl(
    val nodeProvider: NodeRepoNodeProvider
) : ChainExecutableConstructorFactory {

    override fun getConstructor(): ChainExecutableConstructor {
        return ChainExecutableConstructorImpl(nodeProvider)
    }
}