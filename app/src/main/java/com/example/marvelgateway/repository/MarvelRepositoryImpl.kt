package com.example.marvelgateway.repository

import com.example.marvelgateway.domain.entity.Character
import com.example.marvelgateway.domain.entity.Comic
import com.example.marvelgateway.domain.entity.Event
import com.example.marvelgateway.domain.entity.Series
import com.example.marvelgateway.domain.entity.Story
import com.example.marvelgateway.domain.repository.MarvelRepository
import com.example.marvelgateway.repository.datasource.RemoteDataSource
import com.example.marvelgateway.repository.mapper.toEntity
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val remoteDatasource: RemoteDataSource
) : MarvelRepository {
    override suspend fun getCharacters(
        name: String?,
        nameStartsWith: String?,
        limit: Int?,
        offset: Int?
    ): List<Character> {
        return remoteDatasource.getCharacters(name, nameStartsWith, limit, offset)
            .toEntity()
    }

    override suspend fun getCharacterById(characterId: Int): Character {
        return remoteDatasource.getCharacterById(characterId).toEntity()
    }

    override suspend fun getCharacterComics(
        characterId: Int,
        limit: Int,
        offset: Int
    ): List<Comic> {
        return remoteDatasource.getCharacterComics(characterId, limit, offset).toEntity()
    }

    override suspend fun getCharacterEvents(
        characterId: Int,
        limit: Int,
        offset: Int
    ): List<Event> {
        return remoteDatasource.getCharacterEvents(characterId, limit, offset).toEntity()
    }

    override suspend fun getCharacterSeries(
        characterId: Int,
        limit: Int,
        offset: Int
    ): List<Series> {
        return remoteDatasource.getCharacterSeries(characterId, limit, offset).toEntity()
    }

    override suspend fun getCharacterStories(
        characterId: Int,
        limit: Int,
        offset: Int
    ): List<Story> {
        return remoteDatasource.getCharacterStories(characterId, limit, offset).toEntity()
    }

    override suspend fun getComics(
        title: String?,
        titleStartsWith: String?,
        limit: Int?,
        offset: Int?
    ): List<Comic> {
        return remoteDatasource.getComics(title, titleStartsWith, limit, offset).toEntity()
    }

    override suspend fun getSeries(
        title: String?,
        titleStartsWith: String?,
        limit: Int?,
        offset: Int?
    ): List<Series> {
        return remoteDatasource.getSeries(title, titleStartsWith, limit, offset).toEntity()
    }

    override suspend fun getEvents(
        title: String?,
        titleStartsWith: String?,
        limit: Int?,
        offset: Int?
    ): List<Event> {
        return remoteDatasource.getEvents(title, titleStartsWith, limit, offset).toEntity()
    }

    override suspend fun getStories(
        title: String?,
        titleStartsWith: String?,
        limit: Int?,
        offset: Int?
    ): List<Story> {
        return remoteDatasource.getStories(title, titleStartsWith, limit, offset).toEntity()
    }
}