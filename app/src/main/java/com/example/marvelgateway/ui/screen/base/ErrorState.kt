package com.example.marvelgateway.ui.screen.base

sealed interface ErrorState {
    data object NoInternetConnection : ErrorState
    data object Unknown : ErrorState
}