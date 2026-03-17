package edu.oregonstate.cs492finalproject.ui.navigation

sealed class Page(val route: String) {
    object Home: Page("home_page")
    object Detail: Page("detail_page")
    object Search: Page("search_page")
}
