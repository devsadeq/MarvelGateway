package com.example.marvelgateway.repository.mapper

import com.example.marvelgateway.data.remote.response.story.StoryResponse
import com.example.marvelgateway.domain.entity.Story

fun StoryResponse.toEntity() = Story(
    id = id ?: "",
    title = title ?: "",
    description = description ?: "",
    thumbnail = "${thumbnail?.path}.${thumbnail?.extension}"
)

fun List<StoryResponse>.toEntity() = map { it.toEntity() }