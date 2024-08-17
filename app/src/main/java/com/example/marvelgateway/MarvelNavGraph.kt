package com.example.marvelgateway

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.marvelgateway.ui.screen.home.HomeScreen
import com.example.marvelgateway.ui.screen.search.SearchScreen
import kotlinx.serialization.Serializable

@Composable
fun MarvelNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreenRoute
    ) {
        composable<HomeScreenRoute> {
            HomeScreen(navController = navController)
        }
        composable<SearchScreenRoute> {
            SearchScreen(navController = navController)
        }
    }
}

@Serializable
object HomeScreenRoute

@Serializable
object SearchScreenRoute