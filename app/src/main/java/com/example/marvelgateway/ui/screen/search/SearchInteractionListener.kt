package com.example.marvelgateway.ui.screen.search

import com.example.marvelgateway.ui.screen.base.BaseInteractionListener

interface SearchInteractionListener : BaseInteractionListener {
    fun onQueryChanged(query: String)
    fun onCloseSearch()
    fun onSearchClicked()
    fun onSuggestionClicked(suggestion: SearchUIState.Suggestion)
}