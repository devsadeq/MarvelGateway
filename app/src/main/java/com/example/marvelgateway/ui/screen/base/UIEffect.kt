package com.example.marvelgateway.ui.screen.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

interface UIEffect

@Composable
fun <E : UIEffect> CollectUIEffect(
    uiEffect: Flow<E>,
    onEffect: suspend CoroutineScope.(E) -> Unit
) {
    LaunchedEffect(key1 = uiEffect) {
        uiEffect.collectLatest { uiEffect ->
            onEffect(uiEffect)
        }
    }
}