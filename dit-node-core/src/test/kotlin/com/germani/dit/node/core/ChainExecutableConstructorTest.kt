package com.germani.dit.node.core

import com.germani.dit.node.core.model.messaging.Message
import com.germani.dit.node.core.model.messaging.NodeInput
import com.germani.dit.node.core.node.Node
import com.germani.dit.node.core.node.impl.ConcatNode
import com.germani.dit.node.core.node.impl.IdentityNode
import com.germani.dit.node.core.node.impl.MultiplierNode
import com.germani.dit.node.core.node.impl.ReverseNode
import com.germani.dit.node.core.node.impl.UpperNode
import com.germani.dit.node.core.model.preprocessing.ChainDefinition
import com.germani.dit.node.core.model.preprocessing.NodeDefinition
import com.germani.dit.node.core.service.impl.ChainExecutableConstructorImpl
import com.germani.dit.node.core.service.NodeProvider
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono

class ChainExecutableConstructorTest {

    @Test
    fun test() {
        val nodeProvider = object: NodeProvider {
            override fun getNode(id: String): Mono<Node> {
                return when(id) {
                    "1" -> Mono.just(ConcatNode())
                    "2" -> Mono.just(IdentityNode())
                    "3" -> Mono.just(ReverseNode())
                    "4" -> Mono.just(UpperNode())
                    else -> throw RuntimeException()
                }
            }
        }
        val chainExecutableConstructor = ChainExecutableConstructorImpl(nodeProvider)

        val identity = NodeDefinition(1L, "2")
        val reverse = NodeDefinition(2L, "3")
        val upper = NodeDefinition(3L, "4")
        val concat1 = NodeDefinition(4L, "1")
        val concat2 = NodeDefinition(5L, "1")

        identity.childs.add(reverse)
        identity.childs.add(upper)

        reverse.parents.add(identity)
        reverse.childs.add(concat1)

        upper.parents.add(identity)
        upper.childs.add(concat1)
        upper.childs.add(concat2)

        concat1.parents.add(reverse)
        concat1.parents.add(upper)
        concat1.childs.add(concat2)

        concat2.parents.add(concat1)
        concat2.parents.add(upper)

        val chainDefinition = ChainDefinition("1L", identity, concat2)

        val chain = chainExecutableConstructor.construct(chainDefinition)

        val block = chain.block().let {
            it?.process(
                com.germani.dit.node.core.model.messaging.NodeInput(
                    listOf(
                        com.germani.dit.node.core.model.messaging.Message(
                            "ab"
                        )
                    )
                )
            )
        }?.block()!!

        println(block.getMessage())
    }

    @Test
    fun test1() {
        val nodeProvider = object: NodeProvider {
            override fun getNode(id: String): Mono<Node> {
                return when(id) {
                    "1" -> Mono.just(ConcatNode())
                    "2" -> Mono.just(IdentityNode())
                    "3" -> Mono.just(ReverseNode())
                    "4" -> Mono.just(UpperNode())
                    "5" -> Mono.just(MultiplierNode())
                    else -> throw RuntimeException()
                }
            }
        }
        val chainExecutableConstructor = ChainExecutableConstructorImpl(nodeProvider)

        val concat1 = NodeDefinition(1L, "5")
        val concat2 = NodeDefinition(2L, "5")
        val concat3 = NodeDefinition(3L, "5")
        val concat4 = NodeDefinition(4L, "5")
        val concat5 = NodeDefinition(5L, "5")

        concat1.childs.add(concat2)
        concat2.parents.add(concat1)
        concat2.childs.add(concat3)
        concat3.parents.add(concat2)
        concat3.childs.add(concat4)
        concat4.parents.add(concat3)
        concat4.childs.add(concat5)
        concat5.parents.add(concat4)

        val chainDefinition = ChainDefinition("1L", concat1, concat5)

        val chain = chainExecutableConstructor.construct(chainDefinition)

        val block = chain.block().let {
            it?.process(
                com.germani.dit.node.core.model.messaging.NodeInput(
                    listOf(
                        com.germani.dit.node.core.model.messaging.Message(
                            "ab"
                        )
                    )
                )
            )
        }?.block()!!

        println(block.getMessage())
    }

}