package com.example.marvelgateway.domain.usecase

import com.example.marvelgateway.domain.entity.Character
import com.example.marvelgateway.domain.entity.Comic
import com.example.marvelgateway.domain.entity.Event
import com.example.marvelgateway.domain.entity.Series
import com.example.marvelgateway.domain.entity.Story
import com.example.marvelgateway.domain.repository.MarvelRepository
import javax.inject.Inject

interface ManageCharacterUseCase {
    suspend fun getCharacters(
        name: String? = null,
        nameStartsWith: String? = null,
        limit: Int? = null,
        offset: Int? = null
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

class ManageCharacterUseCaseImpl @Inject constructor(
    private val marvelRepository: MarvelRepository
) : ManageCharacterUseCase {
    override suspend fun getCharacters(
        name: String?,
        nameStartsWith: String?,
        limit: Int?,
        offset: Int?
    ): List<Character> {
        return marvelRepository.getCharacters(name, nameStartsWith, limit, offset)
            .also { marvelRepository.insertCharacters(it) }
    }

    override suspend fun getCharacterById(characterId: Int): Character {
        return marvelRepository.getCharacterById(characterId)
    }

    override suspend fun getCharacterComics(
        characterId: Int,
        limit: Int,
        offset: Int
    ): List<Comic> {
        return marvelRepository.getCharacterComics(characterId, limit, offset)
    }

    override suspend fun getCharacterEvents(
        characterId: Int,
        limit: Int,
        offset: Int
    ): List<Event> {
        return marvelRepository.getCharacterEvents(characterId, limit, offset)
    }

    override suspend fun getCharacterSeries(
        characterId: Int,
        limit: Int,
        offset: Int
    ): List<Series> {
        return marvelRepository.getCharacterSeries(characterId, limit, offset)
    }

    override suspend fun getCharacterStories(
        characterId: Int,
        limit: Int,
        offset: Int
    ): List<Story> {
        return marvelRepository.getCharacterStories(characterId, limit, offset)
    }
}