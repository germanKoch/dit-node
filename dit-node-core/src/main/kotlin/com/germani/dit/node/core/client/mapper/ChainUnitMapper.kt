package com.germani.dit.node.core.client.mapper

import com.germani.dit.node.core.client.resource.ChainUnitResource
import com.germani.dit.node.core.config.DefaultMapperConfig
import com.germani.dit.node.core.model.node.ChainUnit
import org.mapstruct.Mapper

@Mapper(
    config = DefaultMapperConfig::class
)
interface ChainUnitMapper {

    fun map(resource: ChainUnitResource): ChainUnit

}