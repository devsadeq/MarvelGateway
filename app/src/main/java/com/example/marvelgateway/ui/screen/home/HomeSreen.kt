package com.example.marvelgateway.ui.screen.home

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.marvelgateway.R
import com.example.marvelgateway.SearchScreenRoute
import com.example.marvelgateway.ViewAllScreenRoute
import com.example.marvelgateway.ui.screen.base.CollectUIEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(pageCount = { uiState.value.bannerImages.size })

    LaunchedEffect(key1 = pagerState.currentPage) {
        launch {
            delay(2000)
            with(pagerState) {
                val target =
                    if (currentPage < uiState.value.bannerImages.count() - 1) currentPage + 1 else 0

                animateScrollToPage(
                    page = target, animationSpec = tween(
                        durationMillis = 0, easing = FastOutLinearInEasing
                    )
                )
            }
        }
    }

    CollectUIEffect(uiEffect = viewModel.uiEffect) {
        when (it) {
            is HomeUIEffect.NavigateToCharacterSearchScreen -> {
                navController.navigate(SearchScreenRoute)
            }

            is HomeUIEffect.NavigateToViewAllScreen -> {
                navController.navigate(ViewAllScreenRoute(it.type))
            }
        }
    }

    HomeScreenContent(
        uiState = uiState.value,
        interactionListener = viewModel,
        pagerState = pagerState,
    )
}

@Composable
private fun HomeScreenContent(
    uiState: HomeUIState,
    interactionListener: HomeInteractionListener,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.background(Color(0xFFF2F2F2)),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MarvelLogo(
                modifier = Modifier.padding(16.dp),
            )
            BannerCarousel(
                pagerState = pagerState,
                bannerImages = uiState.bannerImages,
            )
            CharactersSearchField(
                modifier = Modifier.padding(24.dp),
                onClick = interactionListener::onSearchCharacterClicked
            )
            MarvelSections(
                sections = uiState.sections,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                onSectionClicked = interactionListener::onSectionClicked
            )
        }
    }
}

@Composable
private fun MarvelLogo(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .height(40.dp)
            .fillMaxWidth(),
        painter = painterResource(id = R.drawable.marvel_logo),
        contentDescription = stringResource(R.string.marvel_logo),
    )
}

@Composable
private fun MarvelSections(
    sections: List<HomeUIState.HomeSection>,
    onSectionClicked: (HomeUIState.HomeSection) -> Unit,
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
            MarvelSection(
                section = section,
                onClick = { onSectionClicked(section) }
            )
        }
    }
}

@Composable
private fun MarvelSection(
    section: HomeUIState.HomeSection,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier,
        onClick = onClick,
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFFF8F8F8),
            contentColor = Color.Black
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
        ),
    ) {
        Image(
            modifier = Modifier
                .height(85.dp)
                .fillMaxWidth(),
            painter = painterResource(id = section.coverImage),
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
private fun CharactersSearchField(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(shape = RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = Color(0xFF878686),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp, horizontal = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                tint = Color.Black.copy(alpha = 0.6f),
                contentDescription = ""
            )
            Text(
                text = stringResource(R.string.search_for_marvel_characters),
                style = TextStyle(fontSize = 12.sp, color = Color.Black.copy(alpha = 0.6f)),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
private fun BannerCarousel(
    bannerImages: List<Int>,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        HorizontalPager(
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth(),
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 24.dp),
            pageSize = PageSize.Fill,
            pageSpacing = 24.dp,
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(16.dp))
                    .shadow(elevation = 4.dp),
                painter = painterResource(id = bannerImages[it]),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
            )
        }
        DotsIndicator(
            totalDots = bannerImages.size,
            selectedIndex = pagerState.currentPage
        )
    }
}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center
    ) {
        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.DarkGray)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.LightGray)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}