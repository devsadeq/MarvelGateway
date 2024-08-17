package com.example.marvelgateway.ui.screen.home

import android.util.Log
import com.example.marvelgateway.domain.usecase.ManageCharacterUseCase
import com.example.marvelgateway.ui.screen.base.BaseViewModel
import com.example.marvelgateway.ui.screen.base.ErrorState
import com.example.marvelgateway.ui.screen.base.Stateful
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() :
    BaseViewModel<HomeUIEffect>(), Stateful<HomeUIState>, HomeInteractionListener {
    override val uiState = MutableStateFlow(HomeUIState())

    override fun onSectionClicked(section: HomeUIState.HomeSection) {
        Log.d(TAG, "onSectionClicked: $section")
        sendUiEffect(HomeUIEffect.NavigateToViewAllScreen(section.title))
    }

    override fun onSearchCharacterClicked() {
        Log.d(TAG, "onSearchCharacterClicked: ")
        sendUiEffect(HomeUIEffect.NavigateToCharacterSearchScreen)
    }

    private fun onError(error: ErrorState) {
        Log.e(TAG, "onError: ")
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}