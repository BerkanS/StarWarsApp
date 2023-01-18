package com.berkan.starwarsapp.presentation.people_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items

private const val DEFAULT_ERROR = "Something went wrong.."

@Composable
fun PeopleListScreen(
    viewModel: PeopleListViewModel = hiltViewModel()
) {
    val people = viewModel.getPeople().collectAsLazyPagingItems()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn {
            items(people) { person ->
                person?.let {
                    Surface(
                        modifier = Modifier.padding(24.dp)
                    ) {
                        Text(text = person.name)
                    }
                    Divider()
                }
            }

            when (val state = people.loadState.refresh) {
                is LoadState.Loading -> {
                    item { LoadingItem()  }
                }
                is LoadState.Error -> {
                    item { Text(text = state.error.message ?: DEFAULT_ERROR) }
                }
                else -> {}
            }
        }
    }
}

@Preview
@Composable
fun PreviewList() {
    PeopleListScreen()
}