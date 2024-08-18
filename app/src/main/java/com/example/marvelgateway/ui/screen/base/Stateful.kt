package com.example.marvelgateway.ui.screen.base
import kotlinx.coroutines.flow.StateFlow

interface Stateful<T> {
    val uiState: StateFlow<T>
}