package com.example.marvelgateway.domain.repository

import com.example.marvelgateway.domain.entity.Character
import com.example.marvelgateway.domain.entity.Comic
import com.example.marvelgateway.domain.entity.Event
import com.example.marvelgateway.domain.entity.Series
import com.example.marvelgateway.domain.entity.Story

interface MarvelRepository {
    suspend fun getCharacters(
        name: String?,
        nameStartsWith: String?,
        limit: Int?,
        offset: Int?
    ): List<Character>

    suspend fun getCharacterById(characterId: Int): Character

    suspend fun getCharacterComics(
        characterId: Int,
        limit: Int,
        offset: Int
    ): List<Comic>

    suspend fun getCharacterEvents(
        characterId: Int,
        limit: Int,
        offset: Int
    ): List<Event>

    suspend fun getCharacterSeries(
        characterId: Int,
        limit: Int,
        offset: Int
    ): List<Series>

    suspend fun getCharacterStories(
        characterId: Int,
        limit: Int,
        offset: Int
    ): List<Story>
}