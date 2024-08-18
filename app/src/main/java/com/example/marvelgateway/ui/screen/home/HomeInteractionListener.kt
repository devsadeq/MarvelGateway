package com.example.marvelgateway.ui.screen.home

import com.example.marvelgateway.ui.screen.base.BaseInteractionListener

interface HomeInteractionListener : BaseInteractionListener {
    fun onSectionClicked(section: HomeUIState.HomeSection)
    fun onSearchCharacterClicked()
}