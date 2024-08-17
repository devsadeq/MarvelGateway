package com.example.marvelgateway.ui.screen.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.marvelgateway.R
import com.example.marvelgateway.ui.screen.base.CollectUIEffect

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    CollectUIEffect(uiEffect = viewModel.uiEffect) {
        // TODO: Handle UI effects
    }

    SearchScreenContent(
        uiState = uiState.value,
        interactionListener = viewModel,
    )
}

@Composable
private fun SearchScreenContent(
    uiState: SearchUIState,
    interactionListener: SearchInteractionListener,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                CharacterSearchBar(
                    value = uiState.query,
                    onValueChange = interactionListener::onQueryChanged,
                    onCloseClick = interactionListener::onCloseSearch,
                    onSearch = interactionListener::onSearchClicked,
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
                )
            }
            item {
                AnimatedVisibility(visible = uiState.isLoading) {
                    Column(
                        Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(36.dp),
                            color = Color.Black
                        )
                        Text(
                            text = "Fetching data... Please wait",
                            style = TextStyle(fontSize = 16.sp, color = Color.Black),
                        )
                    }
                }
            }
            items(uiState.suggestions) { suggestion ->
                AnimatedVisibility(visible = uiState.showSuggestions) {
                    Text(
                        text = suggestion.name,
                        style = TextStyle(fontSize = 14.sp),
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 8.dp)
                            .clickable { interactionListener.onSuggestionClicked(suggestion) }
                    )
                }
            }
            item {
                AnimatedVisibility(visible = uiState.showCharacterDetails) {
                    CharacterDetailsCard(
                        character = uiState.character,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
            item {
                AnimatedVisibility(visible = uiState.showComics && uiState.comics.isNotEmpty()) {
                    RelatedSection(
                        title = "Comics",
                        items = uiState.comics,
                        onItemClicked = {},
                        onViewAllClicked = {},
                    )
                }
            }
            item {
                AnimatedVisibility(visible = uiState.showSeries && uiState.series.isNotEmpty()) {
                    RelatedSection(
                        title = "Series",
                        items = uiState.series,
                        onItemClicked = {},
                        onViewAllClicked = {},
                    )
                }
            }
            item {
                AnimatedVisibility(visible = uiState.showEvents && uiState.events.isNotEmpty()) {
                    RelatedSection(
                        title = "Events",
                        items = uiState.events,
                        onItemClicked = {},
                        onViewAllClicked = {},
                    )
                }
            }
            item {
                AnimatedVisibility(visible = uiState.showStories && uiState.stories.isNotEmpty()) {
                    RelatedSection(
                        title = "Stories",
                        items = uiState.stories,
                        onItemClicked = {},
                        onViewAllClicked = {},
                    )
                }
            }
        }
    }
}

@Composable
private fun CharacterSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    onCloseClick: () -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .border(width = 1.dp, color = Color(0xFF4A4A4A), shape = RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            text = stringResource(R.string.search_for_marvel_characters),
                            color = Color.Gray
                        )
                    } else {
                        innerTextField()
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    autoCorrectEnabled = false,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = { onSearch() }
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            IconButton(onClick = onCloseClick) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = stringResource(R.string.close_search),
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color.Black, shape = CircleShape)
                        .padding(2.dp)
                )
            }
        }
    }
}

@Composable
private fun CharacterDetailsCard(
    character: SearchUIState.Character,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                ambientColor = Color(0xFFD1D1D1).copy(25f),
                shape = RoundedCornerShape(16.dp)
            )
            .clip(shape = RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.imageUrl)
                    .build(),
                contentDescription = stringResource(R.string.character_image),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.marvel_logo),
                modifier = Modifier
                    .size(80.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = character.name,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                )
                Text(text = character.description, style = TextStyle(fontSize = 14.sp))
            }
        }
    }
}

@Composable
private fun RelatedSection(
    title: String,
    items: List<SearchUIState.SectionItem>,
    onItemClicked: (SearchUIState.SectionItem) -> Unit,
    onViewAllClicked: (title: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
            )
            Text(
                text = stringResource(R.string.view_all),
                style = TextStyle(fontSize = 12.sp, color = Color.Gray),
                modifier = Modifier.clickable { onViewAllClicked(title) }
            )
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(items) { item ->
                RelatedSectionItem(
                    item = item,
                    onClick = { onItemClicked(item) },
                )
            }
        }
    }
}

@Composable
private fun RelatedSectionItem(
    item: SearchUIState.SectionItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .width(170.dp)
            .height(140.dp),
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
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.coverImageUrl)
                .build(),
            placeholder = painterResource(id = R.drawable.marvel_logo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.marvel_logo),
        )
        Text(
            text = item.title,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier.padding(12.dp)
        )
    }
}