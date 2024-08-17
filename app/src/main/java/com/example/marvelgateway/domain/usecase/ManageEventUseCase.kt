package com.example.marvelgateway.domain.usecase

import com.example.marvelgateway.domain.entity.Event
import com.example.marvelgateway.domain.repository.MarvelRepository
import javax.inject.Inject

interface ManageEventUseCase {
    suspend fun getEvents(
        title: String? = null,
        titleStartsWith: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): List<Event>
}

class ManageEventUseCaseImpl @Inject constructor(
    private val marvelRepository: MarvelRepository,
) : ManageEventUseCase {
    override suspend fun getEvents(
        title: String?,
        titleStartsWith: String?,
        limit: Int?,
        offset: Int?
    ): List<Event> {
        return marvelRepository.getEvents(title, titleStartsWith, limit, offset)
    }
}