package com.example.marvelgateway.repository.mapper

import com.example.marvelgateway.data.remote.response.character.CharacterResponse
import com.example.marvelgateway.domain.entity.Character

fun CharacterResponse.toEntity() = Character(
    id = id ?: "",
    name = name ?: "",
    description = description ?: "",
    thumbnail = "${thumbnail?.path}.${thumbnail?.extension}"
)

fun List<CharacterResponse>.toEntity() = map { it.toEntity() }