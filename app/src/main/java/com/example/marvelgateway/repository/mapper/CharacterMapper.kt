package com.example.marvelgateway.repository.mapper

import com.example.marvelgateway.data.local.entity.CharacterEntity
import com.example.marvelgateway.data.remote.response.character.CharacterResponse
import com.example.marvelgateway.domain.entity.Character

fun CharacterResponse.toEntity() = Character(
    id = id ?: "",
    name = name ?: "",
    description = description ?: "",
    thumbnail = "${thumbnail?.path}.${thumbnail?.extension}",
)

fun List<CharacterResponse>.toEntity() = map { it.toEntity() }

fun CharacterEntity.toDomainEntity() = Character(
    id = id.toString(),
    name = name,
    description = description,
    thumbnail = thumbnail,
)

fun List<CharacterEntity>.toDomainEntity() = map { it.toDomainEntity() }

fun Character.toLocalEntity() = CharacterEntity(
    id = id.toInt(),
    name = name,
    description = description,
    thumbnail = thumbnail,
)

fun List<Character>.toLocalEntity() = map { it.toLocalEntity() }

