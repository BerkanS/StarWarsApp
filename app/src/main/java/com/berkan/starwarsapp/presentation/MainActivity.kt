package com.berkan.starwarsapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
            StarWarsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.PeopleListScreen.route
                    ) {

                        composable(route = Screen.PeopleListScreen.route) {
                            PeopleListScreen(navController = navController)
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


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StarWarsAppTheme {}
}