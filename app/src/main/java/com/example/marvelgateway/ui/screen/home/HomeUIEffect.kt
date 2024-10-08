package com.example.marvelgateway.ui.screen.home

import com.example.marvelgateway.ui.screen.base.UIEffect


interface HomeUIEffect : UIEffect {
    object NavigateToCharacterSearchScreen : HomeUIEffect
    data class NavigateToViewAllScreen(val type: String) : HomeUIEffect
}