package com.example.marvelgateway.ui.screen.viewAll

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.marvelgateway.R
import com.example.marvelgateway.ui.screen.base.CollectUIEffect

@Composable
fun ViewAllScreen(
    viewModel: ViewAllViewModel = hiltViewModel(),
    navController: NavController,
    contentType: String,
    characterId: Int? = null
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = contentType) {
        viewModel.loadContent(contentType, characterId)
    }

    CollectUIEffect(uiEffect = viewModel.uiEffect) {
        when (it) {
            is ViewAllUIEffect.NavigateToBack -> navController.popBackStack()
        }
    }

    ViewAllScreenContent(
        uiState = uiState.value,
        interactionListener = viewModel,
        contentType = contentType,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ViewAllScreenContent(
    uiState: ViewAllUIState,
    interactionListener: ViewAllInteractionListener,
    contentType: String,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = interactionListener::onNavigateToBackClicked) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                title = {
                    Text(text = contentType)
                },
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AnimatedContent(targetState = uiState.isLoading, label = "") {
                if (it) {
                    LoadingIndicator()
                } else {
                    ItemList(
                        sections = uiState.items,
                        onItemClicked = {},
                    )
                }
            }
        }
    }
}

@Composable
private fun ItemList(
    sections: List<ViewAllUIState.Item>,
    onItemClicked: (ViewAllUIState.Item) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier.wrapContentHeight(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 24.dp),
    ) {
        items(sections) { section ->
            Item(
                section = section,
                onClick = { onItemClicked(section) }
            )
        }
    }
}

@Composable
private fun Item(
    section: ViewAllUIState.Item,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier.height(140.dp),
        onClick = onClick,
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFFF8F8F8),
            contentColor = Color.Black
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
        ),
    ) {
        AsyncImage(
            modifier = Modifier
                .height(85.dp)
                .fillMaxWidth(),
            model = section.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Text(
            text = section.title,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Composable
private fun LoadingIndicator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.padding(16.dp),
            color = Color.Black,
        )
        Text(text = "Servers are busy, please wait...")
    }
}