package com.example.marvelgateway.di

import android.content.Context
import androidx.room.Room
import com.example.marvelgateway.data.local.MarvelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideMarvelDatabase(@ApplicationContext context: Context): MarvelDatabase {
        return Room.databaseBuilder(
            context,
            MarvelDatabase::class.java,
            MarvelDatabase.DATABASE_NAME,
        ).build()
    }

    @Singleton
    @Provides
    fun provideCharacterDao(database: MarvelDatabase) = database.characterDao()

    @Singleton
    @Provides
    fun provideComicDao(database: MarvelDatabase) = database.comicDao()

    @Singleton
    @Provides
    fun provideEventDao(database: MarvelDatabase) = database.eventDao()

    @Singleton
    @Provides
    fun provideSeriesDao(database: MarvelDatabase) = database.seriesDao()

    @Singleton
    @Provides
    fun provideStoryDao(database: MarvelDatabase) = database.storyDao()
}