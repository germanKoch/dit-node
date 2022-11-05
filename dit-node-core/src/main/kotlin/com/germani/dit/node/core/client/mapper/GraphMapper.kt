package com.germani.dit.node.core.client.mapper

import com.germani.dit.node.core.client.resource.GraphResource
import com.germani.dit.node.core.config.DefaultMapperConfig
import com.germani.dit.node.core.model.node.Graph
import org.mapstruct.Mapper

@Mapper(
    config = DefaultMapperConfig::class, uses = [GraphUnitMapper::class]
)
interface GraphMapper {

    fun map(resource: GraphResource): Graph


}