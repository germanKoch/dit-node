package com.germani.dit.node.core.service.impl

import com.germani.dit.node.core.model.messaging.NodeInput
import com.germani.dit.node.core.model.messaging.NodeOutput
import com.germani.dit.node.core.service.ChainDefinitionPreprocessor
import com.germani.dit.node.core.service.ChainExecutableConstructorFactory
import com.germani.dit.node.core.service.ChainExecutor
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class MonoChainExecutor(
    private val chainDefinitionPreprocessor: ChainDefinitionPreprocessor,
    private val chainExecutableConstructorFactory: ChainExecutableConstructorFactory
) : ChainExecutor {

    override fun execute(chainId: String, input: NodeInput): Mono<NodeOutput> {
        return chainDefinitionPreprocessor.process(chainId).flatMap {
            chainExecutableConstructorFactory.getConstructor().construct(it)
        }.flatMap { it.process(input) }
    }

}