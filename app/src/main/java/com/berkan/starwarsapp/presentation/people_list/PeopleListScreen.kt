package com.berkan.starwarsapp.presentation.people_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items

@Composable
fun PeopleListScreen(
    viewModel: PeopleListViewModel = hiltViewModel()
) {
    val people = viewModel.getPeople().collectAsLazyPagingItems()

    Box {
        LazyColumn { items(people) { person ->
                person?.let {
                    Surface(
                        modifier = Modifier.padding(24.dp)
                    ) {
                        Text(text = person?.name ?: "empty")
                    }
                }
            }
        }

    }
}