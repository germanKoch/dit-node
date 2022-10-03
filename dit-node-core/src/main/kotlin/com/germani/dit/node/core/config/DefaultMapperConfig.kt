package com.germani.dit.node.core.config

import org.mapstruct.MapperConfig
import org.mapstruct.ReportingPolicy

@MapperConfig(
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface DefaultMapperConfig {
}