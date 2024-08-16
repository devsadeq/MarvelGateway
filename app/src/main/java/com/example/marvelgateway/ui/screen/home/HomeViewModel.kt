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
class HomeViewModel @Inject constructor(
    private val manageCharacter: ManageCharacterUseCase
) :
    BaseViewModel<HomeUIEffect>(), Stateful<HomeUIState>, HomeInteractionListener {
    override val uiState = MutableStateFlow(HomeUIState())

    private fun onError(error: ErrorState) {
        Log.e(TAG, "onError: ")
    }

    fun getCharacters() {
        tryToExecute(
            {
                manageCharacter.getCharacters(
                    limit = 5,
                    offset = 0
                )
            },
            onError = ::onError,
            onSuccess = {
                Log.d(TAG, "init: $it")
            }
        )
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }

    override fun onSectionClicked(section: HomeUIState.HomeSection) {
        Log.d(TAG, "onSectionClicked: $section")
    }

    override fun onSearchCharacterClicked() {
        Log.d(TAG, "onSearchCharacterClicked: ")
    }
}