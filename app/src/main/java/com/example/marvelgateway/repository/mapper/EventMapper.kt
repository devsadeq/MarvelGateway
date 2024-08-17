package com.example.marvelgateway.repository.mapper

import com.example.marvelgateway.data.remote.response.event.EventResponse
import com.example.marvelgateway.domain.entity.Event

fun EventResponse.toEntity() = Event(
    id = id ?: "",
    title = title ?: "",
    description = description ?: "",
    thumbnail = "${thumbnail?.path}.${thumbnail?.extension}"
)

fun List<EventResponse>.toEntity() = map { it.toEntity() }