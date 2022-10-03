package com.germani.dit.node.core.client.mapper

import com.germani.dit.node.core.client.resource.NodeResource
import com.germani.dit.node.core.config.DefaultMapperConfig
import com.germani.dit.node.core.model.node.Node
import org.mapstruct.Mapper

@Mapper(
    config = DefaultMapperConfig::class
)
interface NodeMapper {

    fun map(resource: NodeResource): Node

}