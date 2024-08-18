package com.example.marvelgateway.di

import com.example.marvelgateway.domain.usecase.ManageCharacterUseCase
import com.example.marvelgateway.domain.usecase.ManageCharacterUseCaseImpl
import com.example.marvelgateway.domain.usecase.ManageComicUseCase
import com.example.marvelgateway.domain.usecase.ManageComicUseCaseImpl
import com.example.marvelgateway.domain.usecase.ManageEventUseCase
import com.example.marvelgateway.domain.usecase.ManageEventUseCaseImpl
import com.example.marvelgateway.domain.usecase.ManageSeriesUseCase
import com.example.marvelgateway.domain.usecase.ManageSeriesUseCaseImpl
import com.example.marvelgateway.domain.usecase.ManageStoryUseCase
import com.example.marvelgateway.domain.usecase.ManageStoryUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindManageCharactersUseCase(manageCharactersUseCase: ManageCharacterUseCaseImpl): ManageCharacterUseCase

    @Binds
    @Singleton
    abstract fun bindManageComicsUseCase(manageComicsUseCase: ManageComicUseCaseImpl): ManageComicUseCase

    @Binds
    @Singleton
    abstract fun bindManageEventsUseCase(manageEventsUseCase: ManageEventUseCaseImpl): ManageEventUseCase

    @Binds
    @Singleton
    abstract fun bindManageSeriesUseCase(manageSeriesUseCase: ManageSeriesUseCaseImpl): ManageSeriesUseCase

    @Binds
    @Singleton
    abstract fun bindManageStoriesUseCase(manageStoriesUseCase: ManageStoryUseCaseImpl): ManageStoryUseCase
}