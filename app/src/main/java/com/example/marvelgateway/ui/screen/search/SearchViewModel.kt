package com.example.marvelgateway.ui.screen.search

import android.util.Log
import com.example.marvelgateway.domain.entity.Character
import com.example.marvelgateway.domain.entity.Comic
import com.example.marvelgateway.domain.entity.Event
import com.example.marvelgateway.domain.entity.Series
import com.example.marvelgateway.domain.entity.Story
import com.example.marvelgateway.domain.usecase.ManageCharacterUseCase
import com.example.marvelgateway.ui.screen.base.BaseViewModel
import com.example.marvelgateway.ui.screen.base.ErrorState
import com.example.marvelgateway.ui.screen.base.Stateful
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val manageCharacter: ManageCharacterUseCase
) :
    BaseViewModel<SearchUIEffect>(), Stateful<SearchUIState>, SearchInteractionListener {
    private var searchJob: Job? = null
    override val uiState = MutableStateFlow(SearchUIState())

    override fun onQueryChanged(query: String) {
        Log.d(TAG, "onQueryChanged: ")
        uiState.update {
            it.copy(
                query = query,
                showComics = false,
                showCharacterDetails = false,
                showEvents = false,
                showSeries = false,
                showStories = false,
            )
        }
        lunchSearchJob()
    }

    override fun onCloseSearch() {
        Log.d(TAG, "onCloseSearch: ")
        sendUiEffect(SearchUIEffect.BackToHomeScreen)
    }

    override fun onSearchClicked() {
        Log.d(TAG, "onSearchClicked: ")
        searchForCharacter()
    }

    override fun onSuggestionClicked(suggestion: SearchUIState.Suggestion) {
        Log.d(TAG, "onSuggestionClicked: ")
        uiState.update {
            it.copy(
                query = suggestion.name,
                character = uiState.value.character.copy(id = suggestion.id)
            )
        }
    }

    private fun lunchSearchJob() {
        searchJob?.cancel()
        searchJob = launchDelayed(300L) {
            getSearchSuggestions()
        }
    }

    private fun getSearchSuggestions() {
        Log.d(TAG, "getSearchSuggestions: ")
        uiState.update { it.copy(showSuggestions = false, isLoading = true) }
        tryToExecute(
            { manageCharacter.getCharacters(nameStartsWith = uiState.value.query, limit = 5) },
            onError = ::onError,
            onSuccess = ::onSuggestionsLoadedSuccessfully
        )
    }

    private fun onSuggestionsLoadedSuccessfully(characters: List<Character>) {
        Log.d(TAG, "onSuggestionsLoadedSuccessfully: ")
        uiState.update {
            it.copy(
                suggestions = characters.toSuggestionsUIState(),
                showSuggestions = true,
                isLoading = false
            )
        }
    }

    private fun searchForCharacter() {
        Log.d(TAG, "searchForCharacter: ")
        uiState.update { it.copy(showSuggestions = false, isLoading = true) }
        getCharacterById()
        getCharacterComics()
        getCharacterSeries()
        getCharacterEvents()
        getCharacterStories()
    }

    private fun getCharacterById() {
        Log.d(TAG, "getCharacterById: ${uiState.value.character.id} ")
        tryToExecute(
            { manageCharacter.getCharacterById(uiState.value.character.id.toInt()) },
            onError = ::onError,
            onSuccess = ::onCharactersLoadedSuccessfully
        )
    }

    private fun onCharactersLoadedSuccessfully(character: Character) {
        Log.d(TAG, "onCharactersLoadedSuccessfully: $character ")
        uiState.update {
            it.copy(
                character = character.toUIState(),
                showCharacterDetails = true,
                isLoading = false
            )
        }
    }

    private fun getCharacterComics() {
        Log.d(TAG, "getCharacterComics: ")
        tryToExecute(
            { manageCharacter.getCharacterComics(uiState.value.character.id.toInt(), 10, 0) },
            onError = ::onError,
            onSuccess = ::onComicsLoadedSuccessfully
        )
    }

    private fun onComicsLoadedSuccessfully(comics: List<Comic>) {
        Log.d(TAG, "onComicsLoadedSuccessfully: $comics")
        uiState.update {
            it.copy(
                comics = comics.toComicsUIState(),
                showComics = true
            )
        }
    }

    private fun getCharacterSeries() {
        Log.d(TAG, "getCharacterSeries: ")
        tryToExecute(
            { manageCharacter.getCharacterSeries(uiState.value.character.id.toInt(), 10, 0) },
            onError = ::onError,
            onSuccess = ::onSeriesLoadedSuccessfully
        )
    }

    private fun onSeriesLoadedSuccessfully(series: List<Series>) {
        Log.d(TAG, "onSeriesLoadedSuccessfully: $series")
        uiState.update {
            it.copy(
                series = series.toSeriesUIState(),
                showSeries = true,
            )
        }
    }

    private fun getCharacterEvents() {
        Log.d(TAG, "getCharacterEvents: ")
        tryToExecute(
            { manageCharacter.getCharacterEvents(uiState.value.character.id.toInt(), 10, 0) },
            onError = ::onError,
            onSuccess = ::onEventsLoadedSuccessfully
        )
    }

    private fun onEventsLoadedSuccessfully(events: List<Event>) {
        Log.d(TAG, "onEventsLoadedSuccessfully: $events")
        uiState.update {
            it.copy(
                events = events.toEventsUIState(),
                showEvents = true
            )
        }
    }

    private fun getCharacterStories() {
        Log.d(TAG, "getCharacterStories: ")
        tryToExecute(
            { manageCharacter.getCharacterStories(uiState.value.character.id.toInt(), 10, 0) },
            onError = ::onError,
            onSuccess = ::onStoriesLoadedSuccessfully
        )
    }

    private fun onStoriesLoadedSuccessfully(stories: List<Story>) {
        Log.d(TAG, "onStoriesLoadedSuccessfully: $stories")
        uiState.update {
            it.copy(
                stories = stories.toStoriesUIState(),
                showStories = true
            )
        }
    }

    private fun onError(error: ErrorState) {
        Log.e(TAG, "onError: ")
        uiState.update { it.copy(isLoading = false) }
    }

    companion object {
        private const val TAG = "SearchViewModel"
    }
}