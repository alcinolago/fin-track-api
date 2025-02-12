package com.lagotech.fintrack.application.mapper

import com.github.dozermapper.core.DozerBeanMapperBuilder
import com.github.dozermapper.core.Mapper
import org.springframework.stereotype.Component

@Component
class EntityToDTOMapperImpl : EntityToDTOMapper {

    private val mapper: Mapper = DozerBeanMapperBuilder.buildDefault()

    override fun <O, D> parseObject(origin: O, destination: Class<D>): D {
        return mapper.map(origin, destination)
    }

    override fun <O, D> parseListObjects(origin: List<O>, destination: Class<D>): List<D> {
        return origin.map { mapper.map(it, destination) }
    }
}