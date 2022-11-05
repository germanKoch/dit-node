package com.germani.dit.node.core.config

import com.germani.dit.node.core.model.node.ContextableString
import org.mapstruct.Mapper
import org.mapstruct.MapperConfig
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@MapperConfig(
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [ContextableStringMapper::class]
)
interface DefaultMapperConfig {
}

@Mapper
class ContextableStringMapper {

    fun toContextableString(str: String): ContextableString {
        return ContextableString(str)
    }

}