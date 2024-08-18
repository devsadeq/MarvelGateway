package com.example.marvelgateway.repository.mapper

import com.example.marvelgateway.data.local.entity.SeriesEntity
import com.example.marvelgateway.data.remote.response.series.SeriesResponse
import com.example.marvelgateway.domain.entity.Series

fun SeriesResponse.toEntity() = Series(
    id = id ?: "",
    title = title ?: "",
    description = description ?: "",
    thumbnail = "${thumbnail?.path}.${thumbnail?.extension}"
)

fun List<SeriesResponse>.toEntity() = map { it.toEntity() }

fun SeriesEntity.toDomainEntity() = Series(
    id = id.toString(),
    title = title,
    description = description,
    thumbnail = thumbnail
)

fun List<SeriesEntity>.toDomainEntity() = map { it.toDomainEntity() }

fun Series.toLocalEntity() = SeriesEntity(
    id = id.toInt(),
    title = title,
    description = description,
    thumbnail = thumbnail
)

fun List<Series>.toLocalEntity() = map { it.toLocalEntity() }