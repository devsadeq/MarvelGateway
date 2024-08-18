package com.example.marvelgateway.domain.usecase

import com.example.marvelgateway.domain.entity.Story
import com.example.marvelgateway.domain.repository.MarvelRepository
import javax.inject.Inject

interface ManageStoryUseCase {
    suspend fun getStories(
        title: String? = null,
        titleStartsWith: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): List<Story>
}

class ManageStoryUseCaseImpl @Inject constructor(
    private val marvelRepository: MarvelRepository,
) : ManageStoryUseCase {
    override suspend fun getStories(
        title: String?,
        titleStartsWith: String?,
        limit: Int?,
        offset: Int?
    ): List<Story> {
        val localStories = marvelRepository.getLocalStories()
        return localStories.ifEmpty {
            marvelRepository.getStories(title, titleStartsWith, limit, offset)
                .also { marvelRepository.insertStories(it) }
        }
    }
}