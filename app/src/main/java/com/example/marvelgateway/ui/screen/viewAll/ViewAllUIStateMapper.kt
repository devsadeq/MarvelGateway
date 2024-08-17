package com.example.marvelgateway.ui.screen.viewAll

import com.example.marvelgateway.domain.entity.Character
import com.example.marvelgateway.domain.entity.Comic
import com.example.marvelgateway.domain.entity.Event
import com.example.marvelgateway.domain.entity.Series
import com.example.marvelgateway.domain.entity.Story

fun Character.toCharactersUIState() = ViewAllUIState.Item(
    title = name,
    imageUrl = thumbnail
)

fun List<Character>.toCharactersUIState() = map { it.toCharactersUIState() }

fun Comic.toUIState() = ViewAllUIState.Item(
    title = title,
    imageUrl = thumbnail
)

fun List<Comic>.toComicsUIState() = map { it.toUIState() }

fun Event.toUIState() = ViewAllUIState.Item(
    title = title,
    imageUrl = thumbnail
)

fun List<Event>.toEventsUIState() = map { it.toUIState() }

fun Series.toUIState() = ViewAllUIState.Item(
    title = title,
    imageUrl = thumbnail
)

fun List<Series>.toSeriesUIState() = map { it.toUIState() }

fun Story.toUIState() = ViewAllUIState.Item(
    title = title,
    imageUrl = thumbnail
)

fun List<Story>.toStoriesUIState() = map { it.toUIState() }