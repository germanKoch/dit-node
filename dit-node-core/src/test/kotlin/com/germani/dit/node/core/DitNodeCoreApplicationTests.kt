//package com.germani.dit.node.core
//
//import com.germani.dit.node.core.repo.db.repository.NodeChainRepository
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import reactor.core.publisher.Flux
//import java.time.Duration
//import java.time.temporal.ChronoUnit
//import java.time.temporal.TemporalUnit
//
//@SpringBootTest
//class DitNodeCoreApplicationTests {
//
//    @Autowired
//    private lateinit var repository: NodeChainRepository
//
//    @Test
//    fun deleteAll() {
//        repository.deleteAll().block()
//    }
//
//    @Test
//    fun merge() {
//        var digits = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
//
//        var first = Flux.just(*digits).delayElements(Duration.of(1, ChronoUnit.SECONDS))
//        var second = Flux.just(*digits).delayElements(Duration.of(2, ChronoUnit.SECONDS))
//
//        var merge = Flux.zip(first, second)
//
//        var blockLast = merge.doOnEach { println(it.get()) }.blockLast()
//    }
//
//    @Test
//    fun save() {
//    }
//
//
//    @Test
//    fun get() {
//        var chain = repository.findAll().blockFirst()
//        println(chain)
//    }
//
//}
