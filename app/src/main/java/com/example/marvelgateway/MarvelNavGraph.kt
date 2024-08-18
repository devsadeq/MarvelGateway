package com.example.marvelgateway

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.marvelgateway.ui.screen.home.HomeScreen
import com.example.marvelgateway.ui.screen.search.SearchScreen
import com.example.marvelgateway.ui.screen.viewAll.ViewAllScreen
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
        composable<ViewAllScreenRoute> {
            val args = it.toRoute<ViewAllScreenRoute>()
            ViewAllScreen(
                navController = navController,
                contentType = args.type,
                characterId = args.characterId
            )
        }
    }
}

@Serializable
object HomeScreenRoute

@Serializable
object SearchScreenRoute

@Serializable
data class ViewAllScreenRoute(
    val type: String,
    val characterId: Int = 0
)