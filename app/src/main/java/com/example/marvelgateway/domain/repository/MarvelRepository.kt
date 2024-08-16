package com.example.marvelgateway.domain.repository

import com.example.marvelgateway.domain.entity.Character

interface MarvelRepository {
    suspend fun getCharacters(
        name: String?,
        nameStartsWith: String?,
        limit: Int?,
        offset: Int?
    ): List<Character>
}