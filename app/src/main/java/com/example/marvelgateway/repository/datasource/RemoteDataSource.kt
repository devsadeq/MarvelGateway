package com.example.marvelgateway.repository.datasource

import com.example.marvelgateway.data.remote.response.character.CharacterResponse
import com.example.marvelgateway.data.remote.response.comic.ComicResponse
import com.example.marvelgateway.data.remote.response.event.EventResponse
import com.example.marvelgateway.data.remote.response.series.SeriesResponse
import com.example.marvelgateway.data.remote.response.story.StoryResponse

interface RemoteDataSource {
    suspend fun getCharacters(
        name: String?,
        nameStartsWith: String?,
        limit: Int?,
        offset: Int?
    ): List<CharacterResponse>

    suspend fun getCharacterById(
        characterId: Int
    ): CharacterResponse

    suspend fun getCharacterComics(
        characterId: Int,
        limit: Int,
        offset: Int
    ): List<ComicResponse>

    suspend fun getCharacterEvents(
        characterId: Int,
        limit: Int,
        offset: Int
    ): List<EventResponse>

    suspend fun getCharacterSeries(
        characterId: Int,
        limit: Int,
        offset: Int
    ): List<SeriesResponse>

    suspend fun getCharacterStories(
        characterId: Int,
        limit: Int,
        offset: Int
    ): List<StoryResponse>

}