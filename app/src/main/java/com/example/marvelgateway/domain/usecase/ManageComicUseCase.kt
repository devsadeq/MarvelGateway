package com.example.marvelgateway.domain.usecase

import com.example.marvelgateway.domain.entity.Comic
import com.example.marvelgateway.domain.repository.MarvelRepository
import javax.inject.Inject

interface ManageComicUseCase {
    suspend fun getComics(
        title: String? = null,
        titleStartsWith: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): List<Comic>
}

class ManageComicUseCaseImpl @Inject constructor(
    private val marvelRepository: MarvelRepository,
) : ManageComicUseCase {
    override suspend fun getComics(
        title: String?,
        titleStartsWith: String?,
        limit: Int?,
        offset: Int?
    ): List<Comic> {
        val localComics = marvelRepository.getLocalComics()
        return localComics.ifEmpty {
            marvelRepository.getComics(title, titleStartsWith, limit, offset)
                .also { marvelRepository.insertComics(it) }
        }
    }
}