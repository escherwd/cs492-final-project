package edu.oregonstate.cs492finalproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.oregonstate.cs492finalproject.data.RecordClubRepository
import edu.oregonstate.cs492finalproject.data.RecordClubService
import edu.oregonstate.cs492finalproject.ui.detail.DetailScreen
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
        composable(
            route = Page.Detail.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            val repo = RecordClubRepository(RecordClubService.Companion.create())

            DetailScreen(it.arguments?.getString("id")!!, repo)
        }
//        composable(
//            route = Page.Detail.route,
//        ) {
//            DetailScreen()
//        }
    }
}