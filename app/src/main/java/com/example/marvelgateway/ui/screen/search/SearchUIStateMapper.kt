package com.example.marvelgateway.ui.screen.search

import com.example.marvelgateway.domain.entity.Character
import com.example.marvelgateway.domain.entity.Comic
import com.example.marvelgateway.domain.entity.Event
import com.example.marvelgateway.domain.entity.Series
import com.example.marvelgateway.domain.entity.Story

fun Character.toUIState(): SearchUIState.Character {
    return SearchUIState.Character(
        id = id,
        name = name,
        description = description,
        imageUrl = thumbnail
    )
}

fun Comic.toUIState(): SearchUIState.SectionItem {
    return SearchUIState.SectionItem(
        title = title,
        coverImageUrl = thumbnail
    )
}

fun List<Comic>.toComicsUIState(): List<SearchUIState.SectionItem> {
    return map { it.toUIState() }
}

fun Series.toUIState(): SearchUIState.SectionItem {
    return SearchUIState.SectionItem(
        title = title,
        coverImageUrl = thumbnail
    )
}

fun List<Series>.toSeriesUIState(): List<SearchUIState.SectionItem> {
    return map { it.toUIState() }
}

fun Event.toEventUIState(): SearchUIState.SectionItem {
    return SearchUIState.SectionItem(
        title = title,
        coverImageUrl = thumbnail
    )
}

fun List<Event>.toEventsUIState(): List<SearchUIState.SectionItem> {
    return map { it.toEventUIState() }
}

fun Story.toStoryUIState(): SearchUIState.SectionItem {
    return SearchUIState.SectionItem(
        title = title,
        coverImageUrl = thumbnail
    )
}

fun List<Story>.toStoriesUIState(): List<SearchUIState.SectionItem> {
    return map { it.toStoryUIState() }
}

fun List<Character>.toSuggestionsUIState(): List<SearchUIState.Suggestion> {
    return map { SearchUIState.Suggestion(id = it.id, name = it.name) }
}