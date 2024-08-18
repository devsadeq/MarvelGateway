package com.example.marvelgateway.ui.screen.search

import com.example.marvelgateway.ui.screen.base.UIEffect


interface SearchUIEffect : UIEffect {
    object BackToHomeScreen : SearchUIEffect
    data class NavigateToViewAllScreen(
        val contentType: String,
        val characterId: Int
    ) : SearchUIEffect
}