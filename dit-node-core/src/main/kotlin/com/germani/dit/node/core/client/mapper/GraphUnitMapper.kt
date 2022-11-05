package com.germani.dit.node.core.client.mapper

import com.germani.dit.node.core.client.resource.GraphUnitResource
import com.germani.dit.node.core.config.DefaultMapperConfig
import com.germani.dit.node.core.model.node.GraphUnit
import org.mapstruct.Mapper

@Mapper(
    config = DefaultMapperConfig::class
)
interface GraphUnitMapper {

    fun map(resource: GraphUnitResource): GraphUnit

}