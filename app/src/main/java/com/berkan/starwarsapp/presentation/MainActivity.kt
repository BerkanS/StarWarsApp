package com.berkan.starwarsapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.berkan.starwarsapp.R
import com.berkan.starwarsapp.domain.model.ListType
import com.berkan.starwarsapp.presentation.people_detail.PeopleDetailScreen
import com.berkan.starwarsapp.presentation.people_list.PeopleListScreen
import com.berkan.starwarsapp.presentation.theme.StarWarsAppTheme
import com.berkan.starwarsapp.presentation.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var listType by remember { mutableStateOf( ListType.ALL) }

            StarWarsAppTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()

                    TopAppBar(
                        currentDestination = navBackStackEntry?.destination?.route,
                        onAllClick = { listType = ListType.ALL },
                        onFavoritesClick = { listType = ListType.FAVORITES }
                    )
                    NavHost(
                        navController = navController,
                        startDestination = Screen.PeopleListScreen.route
                    ) {

                        composable(route = Screen.PeopleListScreen.route) {
                            PeopleListScreen(navController = navController, listType = listType)
                        }

                        composable(route = Screen.PeopleDetailScreen.route +
                                "?person={person}",
                            arguments = listOf(
                                navArgument("person") { type = NavType.StringType }
                            )) {
                            val person = it.arguments?.getString("person")
                            PeopleDetailScreen(
                                personJson = person
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopAppBar(
    currentDestination: String?,
    onAllClick: () -> Unit,
    onFavoritesClick: () -> Unit
) {
    val options = arrayOf(ListType.ALL.value, ListType.FAVORITES.value)
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_bar_title)
            )
        },
        actions = {
            if (currentDestination?.startsWith(Screen.PeopleDetailScreen.route) == false) {
                IconButton(
                    onClick = {
                        expanded = true
                    }
                ) {
                    Icon(Icons.Default.Menu, null)
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    },
                ) {
                    options.forEachIndexed { index, value ->
                        DropdownMenuItem(
                            onClick = {
                                expanded = false
                                when (index) {
                                    0 -> onAllClick()
                                    1 -> onFavoritesClick()
                                }
                            }
                        ) {
                            Column {
                                Text(text = value)
                            }
                        }
                    }
                }
            }
        }
    )
}