package com.example.marvelgateway.data.remote

import com.example.marvelgateway.data.remote.response.MarvelResponse
import com.example.marvelgateway.data.remote.response.character.CharacterResponse
import com.example.marvelgateway.data.remote.response.comic.ComicResponse
import com.example.marvelgateway.data.remote.response.event.EventResponse
import com.example.marvelgateway.data.remote.response.series.SeriesResponse
import com.example.marvelgateway.data.remote.response.story.StoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("name") name: String? = null,
        @Query("nameStartsWith") nameStartsWith: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<MarvelResponse<CharacterResponse>>

    @GET("characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: Int
    ): Response<MarvelResponse<CharacterResponse>>

    @GET("characters/{characterId}/comics")
    suspend fun getCharacterComics(
        @Path("characterId") characterId: Int,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<MarvelResponse<ComicResponse>>

    @GET("characters/{characterId}/events")
    suspend fun getCharacterEvents(
        @Path("characterId") characterId: Int,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<MarvelResponse<EventResponse>>

    @GET("characters/{characterId}/series")
    suspend fun getCharacterSeries(
        @Path("characterId") characterId: Int,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<MarvelResponse<SeriesResponse>>

    @GET("characters/{characterId}/stories")
    suspend fun getCharacterStories(
        @Path("characterId") characterId: Int,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<MarvelResponse<StoryResponse>>

    @GET("comics")
    suspend fun getComics(
        @Query("title") title: String? = null,
        @Query("titleStartsWith") titleStartsWith: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<MarvelResponse<ComicResponse>>

    @GET("series")
    suspend fun getSeries(
        @Query("title") title: String? = null,
        @Query("titleStartsWith") titleStartsWith: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<MarvelResponse<SeriesResponse>>

    @GET("events")
    suspend fun getEvents(
        @Query("name") name: String? = null,
        @Query("nameStartsWith") nameStartsWith: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<MarvelResponse<EventResponse>>

    @GET("stories")
    suspend fun getStories(
        @Query("title") title: String? = null,
        @Query("titleStartsWith") titleStartsWith: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<MarvelResponse<StoryResponse>>
}