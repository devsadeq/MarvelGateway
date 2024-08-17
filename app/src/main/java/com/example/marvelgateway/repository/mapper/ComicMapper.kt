package com.example.marvelgateway.repository.mapper

import com.example.marvelgateway.data.remote.response.comic.ComicResponse
import com.example.marvelgateway.domain.entity.Comic

fun ComicResponse.toEntity() = Comic(
    id = id ?: "",
    title = name ?: "",
    description = description ?: "",
    thumbnail = thumbnail?.path + "." + thumbnail?.extension
)

fun List<ComicResponse>.toEntity() = map { it.toEntity() }