package com.germani.dit.node.core.model.exception

class MalformedGraphConfigurationException: RuntimeException {

    constructor(message: String): super(message)

    constructor(message: String, throwable: Throwable): super(message, throwable)
}