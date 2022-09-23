package com.germani.dit.node.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DitNodeCoreApplication
//TODO: reactor coroutine better

fun main(args: Array<String>) {
    runApplication<DitNodeCoreApplication>(*args)
}
