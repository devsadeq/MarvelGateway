package com.example.marvelgateway.ui.screen.viewAll

data class ViewAllUIState(
    val contentType: String = "",
    val characterId: Int = 0,
    val items: List<Item> = emptyList(),
    val isLoading: Boolean = false
) {
    data class Item(
        val title: String,
        val imageUrl: String,
    )

    companion object {
        const val CONTENT_TYPE_COMICS = "Comics"
        const val CONTENT_TYPE_EVENTS = "Events"
        const val CONTENT_TYPE_SERIES = "Series"
        const val CONTENT_TYPE_STORIES = "Stories"
        const val CONTENT_TYPE_CHARACTERS = "Characters"
        const val CONTENT_TYPE_CHARACTER_COMICS = "CharacterComics"
        const val CONTENT_TYPE_CHARACTER_EVENTS = "CharacterEvents"
        const val CONTENT_TYPE_CHARACTER_SERIES = "CharacterSeries"
        const val CONTENT_TYPE_CHARACTER_STORIES = "CharacterStories"
    }
}