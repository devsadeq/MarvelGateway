package com.example.marvelgateway.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import com.example.marvelgateway.ui.screen.base.CollectUIEffect

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    CollectUIEffect(uiEffect = viewModel.uiEffect) {
        // TODO: Handle UI effects
    }

    HomeScreenContent(
        uiState = uiState.value,
        interactionListener = viewModel,
    )
}

@Composable
private fun HomeScreenContent(
    uiState: HomeUIState,
    interactionListener: HomeInteractionListener,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(text = "Home Screen")
            Button(onClick = interactionListener::getCharacters) {
                Text(text = "Get Characters")
            }
        }
    }
}