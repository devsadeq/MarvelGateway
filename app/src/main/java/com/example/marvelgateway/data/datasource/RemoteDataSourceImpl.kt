package com.example.marvelgateway.data.datasource

import android.util.Log
import com.example.marvelgateway.data.remote.MarvelApiService
import com.example.marvelgateway.data.remote.response.MarvelResponse
import com.example.marvelgateway.data.remote.response.character.CharacterResponse
import com.example.marvelgateway.data.remote.response.comic.ComicResponse
import com.example.marvelgateway.data.remote.response.event.EventResponse
import com.example.marvelgateway.data.remote.response.series.SeriesResponse
import com.example.marvelgateway.data.remote.response.story.StoryResponse
import com.example.marvelgateway.repository.datasource.RemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val marvelApiService: MarvelApiService,
) : RemoteDataSource {
    override suspend fun getCharacters(
        name: String?,
        nameStartsWith: String?,
        limit: Int?,
        offset: Int?
    ): List<CharacterResponse> {
        return tryToExecute {
            marvelApiService.getCharacters(name, nameStartsWith, limit, offset)
        }
    }

    override suspend fun getCharacterById(characterId: Int): CharacterResponse {
        return tryToExecute {
            marvelApiService.getCharacterById(characterId)
        }.first()
    }

    override suspend fun getCharacterComics(
        characterId: Int,
        limit: Int,
        offset: Int
    ): List<ComicResponse> {
        return tryToExecute {
            marvelApiService.getCharacterComics(characterId, limit, offset)
        }
    }

    override suspend fun getCharacterEvents(
        characterId: Int,
        limit: Int,
        offset: Int
    ): List<EventResponse> {
        return tryToExecute {
            marvelApiService.getCharacterEvents(characterId, limit, offset)
        }
    }

    override suspend fun getCharacterSeries(
        characterId: Int,
        limit: Int,
        offset: Int
    ): List<SeriesResponse> {
        return tryToExecute {
            marvelApiService.getCharacterSeries(characterId, limit, offset)
        }
    }

    override suspend fun getCharacterStories(
        characterId: Int,
        limit: Int,
        offset: Int
    ): List<StoryResponse> {
        return tryToExecute {
            marvelApiService.getCharacterStories(characterId, limit, offset)
        }
    }

    private suspend fun <T> tryToExecute(func: suspend () -> Response<MarvelResponse<T>>): List<T> {
        val response = func()
        Log.d("TAG", "tryToExecute: ${response.code()}")
        if (response.isSuccessful) {
            return response.body()?.data?.results ?: emptyList()
        }
        throw Exception("Could not fetch data from server ${response.code()}")
    }
}