package com.example.marvelgateway.repository.mapper

import com.example.marvelgateway.data.local.entity.StoryEntity
import com.example.marvelgateway.data.remote.response.story.StoryResponse
import com.example.marvelgateway.domain.entity.Story

fun StoryResponse.toEntity() = Story(
    id = id ?: "",
    title = title ?: "",
    description = description ?: "",
    thumbnail = "${thumbnail?.path}.${thumbnail?.extension}"
)

fun List<StoryResponse>.toEntity() = map { it.toEntity() }

fun StoryEntity.toDomainEntity() = Story(
    id = id.toString(),
    title = title,
    description = description,
    thumbnail = thumbnail
)

fun List<StoryEntity>.toDomainEntity() = map { it.toDomainEntity() }

fun Story.toLocalEntity() = StoryEntity(
    id = id.toInt(),
    title = title,
    description = description,
    thumbnail = thumbnail
)

fun List<Story>.toLocalEntity() = map { it.toLocalEntity() }