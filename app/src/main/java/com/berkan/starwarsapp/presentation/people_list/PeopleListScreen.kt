package com.berkan.starwarsapp.presentation.people_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.berkan.starwarsapp.R
import com.berkan.starwarsapp.presentation.util.ListType
import com.berkan.starwarsapp.domain.model.Person
import com.berkan.starwarsapp.domain.model.toJson
import com.berkan.starwarsapp.presentation.util.Screen

@Composable
fun PeopleListScreen(
    navController: NavController,
    viewModel: PeopleListViewModel = hiltViewModel(),
    listType: ListType
) {
    val people = viewModel.people.collectAsLazyPagingItems()
    val favoritePeople = viewModel.favoritePeople.collectAsState(initial = emptyList())

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        if (listType == ListType.FAVORITES && favoritePeople.value.isEmpty()) {
            Text(
                text = stringResource(id = R.string.empty_state_favorites),
                modifier = Modifier.align(Alignment.Center))
        }

        LazyColumn {
            when (listType) {
                ListType.ALL -> {
                    items(people) { person ->
                        PersonItem(
                            person = person,
                            onItemClick = { personJson ->
                                navController.navigate(
                                    Screen.PeopleDetailScreen.route +
                                            "?person=${personJson}"
                                )
                            },
                            onFavorite = {
                                viewModel.favoritePerson(it)
                            },
                            onUnfavorite = {
                                viewModel.unfavoritePerson(it)
                            },
                            isFavorite = favoritePeople.value.contains(person)
                        )
                    }

                    if (people.loadState.append == LoadState.Loading) {
                        item {
                            LoadingItem()
                        }
                    }

                    when (val state = people.loadState.refresh) {
                        is LoadState.Loading -> {
                            item {
                                LoadingItem()
                            }
                        }
                        is LoadState.Error -> {
                            item {
                                Text(
                                    text = state.error.message
                                        ?: stringResource(id = R.string.default_error)
                                )
                            }
                        }
                        else -> {}
                    }
                }
                ListType.FAVORITES -> {
                    items(favoritePeople.value) { person ->
                        PersonItem(
                            person = person,
                            onItemClick = { personJson ->
                                navController.navigate(
                                    Screen.PeopleDetailScreen.route +
                                            "?person=${personJson}"
                                )
                            },
                            onFavorite = {
                                viewModel.favoritePerson(it)
                            },
                            onUnfavorite = {
                                viewModel.unfavoritePerson(it)
                            },
                            isFavorite = favoritePeople.value.contains(person)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun PersonItem(
    person: Person?,
    onItemClick: (json: String) -> Unit,
    onFavorite: (person: Person) -> Unit,
    onUnfavorite: (person: Person) -> Unit,
    isFavorite: Boolean
) {
    person?.let {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val personJson = it.toJson()
                    onItemClick(personJson)
                }
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = person.name
            )
            IconButton(
                onClick = {
                    if (isFavorite) onUnfavorite(it) else onFavorite(it)
                }
            ) {
                Icon(
                    if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder, null,
                    modifier = Modifier.wrapContentWidth(),
                    tint = Color.Red
                )
            }
        }
        Divider()
    }
}

