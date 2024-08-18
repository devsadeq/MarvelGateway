package com.example.marvelgateway.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
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

@Database(
    entities = [
        CharacterEntity::class,
        EventEntity::class,
        ComicEntity::class,
        StoryEntity::class,
        SeriesEntity::class

    ], version = 1
)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun eventDao(): EventDao
    abstract fun comicDao(): ComicDao
    abstract fun storyDao(): StoryDao
    abstract fun seriesDao(): SeriesDao

    companion object {
        const val DATABASE_NAME = "marvel_db"
    }
}