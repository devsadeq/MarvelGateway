package com.example.marvelgateway.repository.mapper

import com.example.marvelgateway.data.remote.response.series.SeriesResponse
import com.example.marvelgateway.domain.entity.Series

fun SeriesResponse.toEntity() = Series(
    id = id ?: "",
    title = title ?: "",
    description = description ?: "",
    thumbnail = "${thumbnail?.path}.${thumbnail?.extension}"
)

fun List<SeriesResponse>.toEntity() = map { it.toEntity() }