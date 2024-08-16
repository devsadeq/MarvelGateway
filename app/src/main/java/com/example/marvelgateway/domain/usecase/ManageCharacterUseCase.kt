package com.example.marvelgateway.domain.usecase

import com.example.marvelgateway.domain.entity.Character
import com.example.marvelgateway.domain.repository.MarvelRepository
import javax.inject.Inject

interface ManageCharacterUseCase {
    suspend fun getCharacters(
        name: String? = null,
        nameStartsWith: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): List<Character>
}

class ManageCharacterUseCaseImpl @Inject constructor(
    private val marvelRepository: MarvelRepository
) : ManageCharacterUseCase {
    override suspend fun getCharacters(
        name: String?,
        nameStartsWith: String?,
        limit: Int?,
        offset: Int?
    ): List<Character> {
        return marvelRepository.getCharacters(name, nameStartsWith, limit, offset)
    }
}