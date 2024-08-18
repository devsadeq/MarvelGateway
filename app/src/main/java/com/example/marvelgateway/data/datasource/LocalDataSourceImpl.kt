package com.example.marvelgateway.data.datasource

import com.example.marvelgateway.data.local.dao.CharacterDao
import com.example.marvelgateway.data.local.dao.ComicDao
import com.example.marvelgateway.data.local.dao.EventDao
import com.example.marvelgateway.data.local.dao.SeriesDao
import com.example.marvelgateway.data.local.dao.StoryDao
import com.example.marvelgateway.data.local.entity.CharacterEntity
import com.example.marvelgateway.data.local.entity.ComicEntity
import com.example.marvelgateway.data.local.entity.EventEntity
import com.example.marvelgateway.data.local.entity.SeriesEntity
import com.example.marvelgateway.data.local.entity.StoryEntity
import com.example.marvelgateway.repository.datasource.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val characterDao: CharacterDao,
    private val comicDao: ComicDao,
    private val eventDao: EventDao,
    private val seriesDao: SeriesDao,
    private val storyDao: StoryDao
) : LocalDataSource {
    override suspend fun insertCharacters(characters: List<CharacterEntity>) {
        characterDao.insertCharacters(characters)
    }

    override suspend fun insertComics(comics: List<ComicEntity>) {
        comicDao.insertComics(comics)
    }

    override suspend fun insertSeries(series: List<SeriesEntity>) {
        seriesDao.insertSeries(series)
    }

    override suspend fun insertEvents(events: List<EventEntity>) {
        eventDao.insertEvents(events)
    }

    override suspend fun insertStories(stories: List<StoryEntity>) {
        storyDao.insertStories(stories)
    }

    override suspend fun getLocalCharacters(
        nameStartsWith: String?,
    ): List<CharacterEntity> {
        return characterDao.getAllCharacters(nameStartsWith)
    }

    override suspend fun getLocalComics(): List<ComicEntity> {
        return comicDao.getAllComics()
    }

    override suspend fun getLocalSeries(): List<SeriesEntity> {
        return seriesDao.getAllSeries()
    }

    override suspend fun getLocalEvents(): List<EventEntity> {
        return eventDao.getAllEvents()
    }

    override suspend fun getLocalStories(): List<StoryEntity> {
        return storyDao.getAllStories()
    }
}