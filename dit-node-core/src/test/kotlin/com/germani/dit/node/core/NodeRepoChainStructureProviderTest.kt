package com.germani.dit.node.core

import com.germani.dit.node.core.service.impl.CacheableChainDefinitionPreprocessor
import com.germani.dit.node.core.service.impl.NodeRepoChainStructureProvider
import org.junit.jupiter.api.Test

class NodeRepoChainStructureProviderTest {

    @Test
    fun test() {
        var cacheableChainDefinitionPreprocessor =
            CacheableChainDefinitionPreprocessor(NodeRepoChainStructureProvider())
        var process = cacheableChainDefinitionPreprocessor.process("1L").block()
        println(process)
    }

}