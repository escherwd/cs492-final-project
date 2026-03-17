package edu.oregonstate.cs492finalproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.oregonstate.cs492finalproject.ui.pages.HomePage
import edu.oregonstate.cs492finalproject.ui.pages.SearchPage

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Page.Home.route) {
        composable(route = Page.Home.route) {
            HomePage(navigationController = navController)
        }
        composable(
            route = Page.Search.route,
        ) {
            SearchPage(navigationController = navController)
        }
    }
}