package com.germani.dit.node.repo.config

import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@EnableReactiveMongoRepositories
class MongoReactiveApplication : AbstractReactiveMongoConfiguration() {
    override fun getDatabaseName(): String {
        return "dit-node"
    }

    //TODO: add credentials
    override fun reactiveMongoClient(): MongoClient {
        return MongoClients.create();
    }
}