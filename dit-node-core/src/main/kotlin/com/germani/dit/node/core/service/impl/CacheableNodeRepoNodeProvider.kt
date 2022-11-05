package com.germani.dit.node.core.service.impl

import com.germani.dit.node.core.client.RepoNodeAdapter
import com.germani.dit.node.core.node.ExecutableNode
import com.germani.dit.node.core.node.ExecutableNodeFactory
import com.germani.dit.node.core.service.NodeProvider
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.concurrent.ConcurrentHashMap

@Service
class CacheableNodeRepoNodeProvider(
    private val nodeAdapter: RepoNodeAdapter,
    factories: List<ExecutableNodeFactory>
) : NodeProvider {

    private val cache: MutableMap<String, ExecutableNode> = ConcurrentHashMap()
    private val nodeFactories = factories.associateBy { it.getType() }

    override fun getNode(id: String): Mono<ExecutableNode> {
        return if (cache.containsKey(id)) {
            Mono.just(cache[id]!!)
        } else {
            nodeAdapter.getNode(id).mapNotNull { nodeFactories[it.nodeType]?.getNode(it) }
        }
    }
}