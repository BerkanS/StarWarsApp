package com.berkan.starwarsapp.presentation.people_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PeopleListScreen(
    viewModel: PeopleListViewModel = hiltViewModel()
) {
    Box {
        viewModel.listOfPeople.let { list ->
            LazyColumn(
                content = {
                    items(list) { person ->
                        Surface(
                            modifier = Modifier.padding(24.dp)
                        ) {
                            Text(text = person.name)
                        }
                    }
            })
        }

    }
}