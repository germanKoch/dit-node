package com.germani.dit.node.core.service.impl

import com.germani.dit.node.core.client.RepoGraphAdapter
import com.germani.dit.node.core.model.exception.InconsistentGraphStateException
import com.germani.dit.node.core.model.node.Graph
import com.germani.dit.node.core.service.GraphProvider
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.concurrent.ConcurrentHashMap

@Service
class CacheableGraphProvider(
    val repoGraphAdapter: RepoGraphAdapter
) : GraphProvider {

    private val cache: MutableMap<String, Graph> = ConcurrentHashMap()

    override fun provide(id: String): Mono<Graph> {
        return if (cache.containsKey(id)) {
            Mono.just(cache[id]!!)
        } else {
            repoGraphAdapter.getGraph(id).doOnEach {
                if (it.hasValue()) {
                    cache[id] = it.get()!!
                } else {
                    throw InconsistentGraphStateException("Graph can not be found")
                }
            }
        }
    }

}