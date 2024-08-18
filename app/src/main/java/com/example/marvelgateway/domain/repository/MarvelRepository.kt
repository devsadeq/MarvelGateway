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

    suspend fun getComics(
        title: String?,
        titleStartsWith: String?,
        limit: Int?,
        offset: Int?
    ): List<Comic>

    suspend fun getSeries(
        title: String?,
        titleStartsWith: String?,
        limit: Int?,
        offset: Int?
    ): List<Series>

    suspend fun getEvents(
        title: String?,
        titleStartsWith: String?,
        limit: Int?,
        offset: Int?
    ): List<Event>

    suspend fun getStories(
        title: String?,
        titleStartsWith: String?,
        limit: Int?,
        offset: Int?
    ): List<Story>

    suspend fun insertCharacters(characters: List<Character>)

    suspend fun insertComics(comics: List<Comic>)

    suspend fun insertSeries(series: List<Series>)

    suspend fun insertEvents(events: List<Event>)

    suspend fun insertStories(stories: List<Story>)

    suspend fun getLocalCharacters(
        nameStartsWith: String? = null,
    ): List<Character>

    suspend fun getLocalComics(): List<Comic>

    suspend fun getLocalSeries(): List<Series>

    suspend fun getLocalEvents(): List<Event>

    suspend fun getLocalStories(): List<Story>
}