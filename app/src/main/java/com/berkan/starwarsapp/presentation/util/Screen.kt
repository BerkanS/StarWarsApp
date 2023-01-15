package com.berkan.starwarsapp.presentation.util

sealed class Screen(val route: String) {
    object PeopleListScreen: Screen("people_list_screen")
    object PeopleDetailScreen: Screen("people_detail_screen")
}