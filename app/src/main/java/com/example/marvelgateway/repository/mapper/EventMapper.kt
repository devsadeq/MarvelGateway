package com.example.marvelgateway.repository.mapper

import com.example.marvelgateway.data.local.entity.EventEntity
import com.example.marvelgateway.data.remote.response.event.EventResponse
import com.example.marvelgateway.domain.entity.Event

fun EventResponse.toEntity() = Event(
    id = id ?: "",
    title = title ?: "",
    description = description ?: "",
    thumbnail = "${thumbnail?.path}.${thumbnail?.extension}"
)

fun List<EventResponse>.toEntity() = map { it.toEntity() }

fun EventEntity.toDomainEntity() = Event(
    id = id.toString(),
    title = title,
    description = description,
    thumbnail = thumbnail
)

fun List<EventEntity>.toDomainEntity() = map { it.toDomainEntity() }

fun Event.toLocalEntity() = EventEntity(
    id = id.toInt(),
    title = title,
    description = description,
    thumbnail = thumbnail
)

fun List<Event>.toLocalEntity() = map { it.toLocalEntity() }