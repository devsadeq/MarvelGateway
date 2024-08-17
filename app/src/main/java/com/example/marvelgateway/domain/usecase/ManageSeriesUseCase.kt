package com.example.marvelgateway.domain.usecase

import com.example.marvelgateway.domain.entity.Series
import com.example.marvelgateway.domain.repository.MarvelRepository
import javax.inject.Inject

interface ManageSeriesUseCase {
    suspend fun getSeries(
        title: String? = null,
        titleStartsWith: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): List<Series>
}

class ManageSeriesUseCaseImpl @Inject constructor(
    private val marvelRepository: MarvelRepository,
) : ManageSeriesUseCase {
    override suspend fun getSeries(
        title: String?,
        titleStartsWith: String?,
        limit: Int?,
        offset: Int?
    ): List<Series> {
        return marvelRepository.getSeries(title, titleStartsWith, limit, offset)
    }
}