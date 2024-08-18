package com.example.marvelgateway.di

import com.example.marvelgateway.domain.repository.MarvelRepository
import com.example.marvelgateway.repository.MarvelRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMarvelRepository(marvelRepository: MarvelRepositoryImpl): MarvelRepository
}