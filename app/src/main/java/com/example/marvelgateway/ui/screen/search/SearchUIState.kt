package com.example.marvelgateway.ui.screen.search

data class SearchUIState(
    val query: String = "",
    val suggestions: List<Suggestion> = emptyList(),
    val character: Character = Character(),
    val comics: List<SectionItem> = emptyList(),
    val series: List<SectionItem> = emptyList(),
    val events: List<SectionItem> = emptyList(),
    val stories: List<SectionItem> = emptyList(),
    val isLoading: Boolean = false,
    val showSuggestions: Boolean = false,
    val showCharacterDetails: Boolean = false,
    val showComics: Boolean = false,
    val showSeries: Boolean = false,
    val showEvents: Boolean = false,
    val showStories: Boolean = false
) {
    data class Character(
        val id: String = "",
        val name: String = "",
        val description: String = "",
        val imageUrl: String = ""
    )

    data class SectionItem(
        val title: String,
        val coverImageUrl: String,
    )

    data class Suggestion(
        val id: String,
        val name: String,
    )
}