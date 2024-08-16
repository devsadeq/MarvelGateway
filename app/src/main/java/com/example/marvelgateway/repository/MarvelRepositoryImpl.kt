package com.example.marvelgateway.repository

import com.example.marvelgateway.domain.entity.Character
import com.example.marvelgateway.domain.repository.MarvelRepository
import com.example.marvelgateway.repository.datasource.RemoteDataSource
import com.example.marvelgateway.repository.mapper.toEntity
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val remoteDatasource: RemoteDataSource
) : MarvelRepository {
    override suspend fun getCharacters(
        name: String?,
        nameStartsWith: String?,
        limit: Int?,
        offset: Int?
    ): List<Character> {
        return remoteDatasource.getCharacters(name, nameStartsWith, limit, offset)
            .toEntity()
    }
}