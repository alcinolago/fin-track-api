package com.lagotech.fintrack.application.mapper

interface EntityToDTOMapper {

    fun <O, D> parseObject(origin: O, destination: Class<D>): D

    fun <O, D> parseListObjects(origin: List<O>, destination: Class<D>): List<D>
}