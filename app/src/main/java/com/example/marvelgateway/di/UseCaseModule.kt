package com.example.marvelgateway.di

import com.example.marvelgateway.domain.usecase.ManageCharacterUseCase
import com.example.marvelgateway.domain.usecase.ManageCharacterUseCaseImpl
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
}