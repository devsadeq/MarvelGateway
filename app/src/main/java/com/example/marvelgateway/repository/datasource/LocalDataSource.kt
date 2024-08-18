package com.example.marvelgateway.repository.datasource

import com.example.marvelgateway.data.local.entity.CharacterEntity
import com.example.marvelgateway.data.local.entity.ComicEntity
import com.example.marvelgateway.data.local.entity.EventEntity
import com.example.marvelgateway.data.local.entity.SeriesEntity
import com.example.marvelgateway.data.local.entity.StoryEntity

interface LocalDataSource {
    suspend fun insertCharacters(characters: List<CharacterEntity>)
    suspend fun insertComics(comics: List<ComicEntity>)
    suspend fun insertSeries(series: List<SeriesEntity>)
    suspend fun insertEvents(events: List<EventEntity>)
    suspend fun insertStories(stories: List<StoryEntity>)

    suspend fun getLocalCharacters(
        nameStartsWith: String? = null,
    ): List<CharacterEntity>

    suspend fun getLocalComics(): List<ComicEntity>
    suspend fun getLocalSeries(): List<SeriesEntity>
    suspend fun getLocalEvents(): List<EventEntity>
    suspend fun getLocalStories(): List<StoryEntity>
}