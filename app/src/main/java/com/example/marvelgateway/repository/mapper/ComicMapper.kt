package com.example.marvelgateway.repository.mapper

import com.example.marvelgateway.data.local.entity.ComicEntity
import com.example.marvelgateway.data.remote.response.comic.ComicResponse
import com.example.marvelgateway.domain.entity.Comic

fun ComicResponse.toEntity() = Comic(
    id = id ?: "",
    title = name ?: "",
    description = description ?: "",
    thumbnail = thumbnail?.path + "." + thumbnail?.extension
)

fun List<ComicResponse>.toEntity() = map { it.toEntity() }

fun ComicEntity.toDomainEntity() = Comic(
    id = id.toString(),
    title = title,
    description = description,
    thumbnail = thumbnail
)

fun List<ComicEntity>.toDomainEntity() = map { it.toDomainEntity() }

fun Comic.toLocalEntity() = ComicEntity(
    id = id.toInt(),
    title = title,
    description = description,
    thumbnail = thumbnail
)

fun List<Comic>.toLocalEntity() = map { it.toLocalEntity() }