package com.germani.dit.node.core.client.mapper

import com.germani.dit.node.core.client.resource.ChainResource
import com.germani.dit.node.core.config.DefaultMapperConfig
import com.germani.dit.node.core.model.node.Chain
import org.mapstruct.Mapper

@Mapper(
    config = DefaultMapperConfig::class, uses = [ChainUnitMapper::class]
)
interface ChainMapper {

    fun map(resource: ChainResource): Chain


}