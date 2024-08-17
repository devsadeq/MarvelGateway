package com.example.marvelgateway.ui.screen.viewAll

import android.util.Log
import com.example.marvelgateway.domain.entity.Character
import com.example.marvelgateway.domain.entity.Comic
import com.example.marvelgateway.domain.entity.Event
import com.example.marvelgateway.domain.entity.Series
import com.example.marvelgateway.domain.entity.Story
import com.example.marvelgateway.domain.usecase.ManageCharacterUseCase
import com.example.marvelgateway.domain.usecase.ManageComicUseCase
import com.example.marvelgateway.domain.usecase.ManageEventUseCase
import com.example.marvelgateway.domain.usecase.ManageSeriesUseCase
import com.example.marvelgateway.domain.usecase.ManageStoryUseCase
import com.example.marvelgateway.ui.screen.base.BaseViewModel
import com.example.marvelgateway.ui.screen.base.ErrorState
import com.example.marvelgateway.ui.screen.base.Stateful
import com.example.marvelgateway.ui.screen.viewAll.ViewAllUIState.Companion.CONTENT_TYPE_CHARACTERS
import com.example.marvelgateway.ui.screen.viewAll.ViewAllUIState.Companion.CONTENT_TYPE_CHARACTER_COMICS
import com.example.marvelgateway.ui.screen.viewAll.ViewAllUIState.Companion.CONTENT_TYPE_CHARACTER_EVENTS
import com.example.marvelgateway.ui.screen.viewAll.ViewAllUIState.Companion.CONTENT_TYPE_CHARACTER_SERIES
import com.example.marvelgateway.ui.screen.viewAll.ViewAllUIState.Companion.CONTENT_TYPE_CHARACTER_STORIES
import com.example.marvelgateway.ui.screen.viewAll.ViewAllUIState.Companion.CONTENT_TYPE_COMICS
import com.example.marvelgateway.ui.screen.viewAll.ViewAllUIState.Companion.CONTENT_TYPE_EVENTS
import com.example.marvelgateway.ui.screen.viewAll.ViewAllUIState.Companion.CONTENT_TYPE_SERIES
import com.example.marvelgateway.ui.screen.viewAll.ViewAllUIState.Companion.CONTENT_TYPE_STORIES
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ViewAllViewModel @Inject constructor(
    private val manageComic: ManageComicUseCase,
    private val manageEvent: ManageEventUseCase,
    private val manageSeries: ManageSeriesUseCase,
    private val manageStory: ManageStoryUseCase,
    private val manageCharacter: ManageCharacterUseCase
) :
    BaseViewModel<ViewAllUIEffect>(), Stateful<ViewAllUIState>, ViewAllInteractionListener {
    override val uiState = MutableStateFlow(ViewAllUIState())

    fun loadContent(contentType: String, characterId: Int? = null) {
        Log.d(TAG, "loadContent: $contentType")
        Log.d(TAG, "loadContent: $characterId")
        uiState.update {
            it.copy(
                contentType = contentType,
                characterId = characterId ?: 0
            )
        }
        when (contentType) {
            CONTENT_TYPE_COMICS -> getComics()
            CONTENT_TYPE_EVENTS -> getEvents()
            CONTENT_TYPE_SERIES -> getSeries()
            CONTENT_TYPE_STORIES -> getStories()
            CONTENT_TYPE_CHARACTERS -> getCharacters()
            CONTENT_TYPE_CHARACTER_COMICS -> getCharacterComics()
            CONTENT_TYPE_CHARACTER_EVENTS -> getCharacterEvents()
            CONTENT_TYPE_CHARACTER_SERIES -> getCharacterSeries()
            CONTENT_TYPE_CHARACTER_STORIES -> getCharacterStories()
            else -> Unit
        }
    }

    override fun onNavigateToBackClicked() {
        Log.d(TAG, "onNavigateToBackClicked: ")
        sendUiEffect(ViewAllUIEffect.NavigateToBack)
    }

    private fun getCharacters() {
        Log.d(TAG, "getCharacters: ")
        uiState.update { it.copy(isLoading = true) }
        tryToExecute(
            manageCharacter::getCharacters,
            onError = ::onError,
            onSuccess = ::onGetCharactersSuccess
        )
    }

    private fun onGetCharactersSuccess(characters: List<Character>) {
        Log.d(TAG, "onGetCharactersSuccess: ")
        uiState.update {
            it.copy(
                items = characters.toCharactersUIState(),
                isLoading = false
            )
        }
    }

    private fun getComics() {
        Log.d(TAG, "getComics: ")
        uiState.update { it.copy(isLoading = true) }
        tryToExecute(
            manageComic::getComics,
            onError = ::onError,
            onSuccess = ::onGetComicsSuccess
        )
    }

    private fun onGetComicsSuccess(comics: List<Comic>) {
        Log.d(TAG, "onGetComicsSuccess: ")
        uiState.update {
            it.copy(
                items = comics.toComicsUIState(),
                isLoading = false
            )
        }
    }

    private fun getEvents() {
        Log.d(TAG, "getEvents: ")
        uiState.update { it.copy(isLoading = true) }
        tryToExecute(
            manageEvent::getEvents,
            onError = ::onError,
            onSuccess = ::onGetEventsSuccess
        )
    }

    private fun onGetEventsSuccess(events: List<Event>) {
        Log.d(TAG, "onGetEventsSuccess: ")
        uiState.update {
            it.copy(
                items = events.toEventsUIState(),
                isLoading = false
            )
        }
    }

    private fun getSeries() {
        Log.d(TAG, "getSeries: ")
        uiState.update { it.copy(isLoading = true) }
        tryToExecute(
            manageSeries::getSeries,
            onError = ::onError,
            onSuccess = ::onGetSeriesSuccess
        )
    }

    private fun onGetSeriesSuccess(series: List<Series>) {
        Log.d(TAG, "onGetSeriesSuccess: ")
        uiState.update {
            it.copy(
                items = series.toSeriesUIState(),
                isLoading = false
            )
        }
    }

    private fun getStories() {
        Log.d(TAG, "getStories: ")
        uiState.update { it.copy(isLoading = true) }
        tryToExecute(
            manageStory::getStories,
            onError = ::onError,
            onSuccess = ::onGetStoriesSuccess
        )
    }

    private fun onGetStoriesSuccess(stories: List<Story>) {
        Log.d(TAG, "onGetStoriesSuccess: ")
        uiState.update {
            it.copy(
                items = stories.toStoriesUIState(),
                isLoading = false
            )
        }
    }

    private fun getCharacterComics() {
        Log.d(TAG, "getCharacterComics: ${uiState.value.characterId}")
        uiState.update { it.copy(isLoading = true) }
        tryToExecute(
            {
                manageCharacter.getCharacterComics(
                    characterId = uiState.value.characterId,
                    limit = 20,
                    offset = 0
                )
            },
            onError = ::onError,
            onSuccess = ::onGetComicsSuccess
        )
    }

    private fun getCharacterEvents() {
        Log.d(TAG, "getCharacterEvents: ${uiState.value.characterId}")
        uiState.update { it.copy(isLoading = true) }
        tryToExecute(
            {
                manageCharacter.getCharacterEvents(
                    characterId = uiState.value.characterId,
                    limit = 20,
                    offset = 0
                )
            },
            onError = ::onError,
            onSuccess = ::onGetEventsSuccess
        )
    }

    private fun getCharacterSeries() {
        Log.d(TAG, "getCharacterSeries: ${uiState.value.characterId}")
        uiState.update { it.copy(isLoading = true) }
        tryToExecute(
            {
                manageCharacter.getCharacterSeries(
                    characterId = uiState.value.characterId,
                    limit = 20,
                    offset = 0
                )
            },
            onError = ::onError,
            onSuccess = ::onGetSeriesSuccess
        )
    }

    private fun getCharacterStories() {
        Log.d(TAG, "getCharacterStories: ${uiState.value.characterId}")
        uiState.update { it.copy(isLoading = true) }
        tryToExecute(
            {
                manageCharacter.getCharacterStories(
                    characterId = uiState.value.characterId,
                    limit = 20,
                    offset = 0
                )
            },
            onError = ::onError,
            onSuccess = ::onGetStoriesSuccess
        )
    }

    private fun onError(error: ErrorState) {
        Log.e(TAG, "onError: ")
        uiState.update { it.copy(isLoading = false) }
    }

    companion object {
        private const val TAG = "ViewAllViewModel"
    }
}