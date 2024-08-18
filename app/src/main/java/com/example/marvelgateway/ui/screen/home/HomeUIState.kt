package com.example.marvelgateway.ui.screen.home

import com.example.marvelgateway.R

data class HomeUIState(
    val bannerImages: List<Int> = listOf(
        R.drawable.cartoons_cover,
        R.drawable.characters_cover,
        R.drawable.series_cover,
        R.drawable.stories_cover,
        R.drawable.events_cover,
    ),
    val sections: List<HomeSection> = listOf(
        HomeSection(
            title = "Characters",
            coverImage = R.drawable.characters_cover
        ),
        HomeSection(
            title = "Comics",
            coverImage = R.drawable.comics_cover
        ),
        HomeSection(
            title = "Cartoons",
            coverImage = R.drawable.cartoons_cover
        ),
        HomeSection(
            title = "Events",
            coverImage = R.drawable.events_cover
        ),
        HomeSection(
            title = "Series",
            coverImage = R.drawable.series_cover
        ),
        HomeSection(
            title = "Stories",
            coverImage = R.drawable.stories_cover
        ),
    ),
    val isLoading: Boolean = false
) {
    data class HomeSection(
        val title: String,
        val coverImage: Int,
    )
}
