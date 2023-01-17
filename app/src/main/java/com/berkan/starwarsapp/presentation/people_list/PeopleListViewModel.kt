package com.berkan.starwarsapp.presentation.people_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.berkan.starwarsapp.domain.model.Person
import com.berkan.starwarsapp.domain.repository.StarWarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleListViewModel @Inject constructor(
    private val repository: StarWarsRepository
) : ViewModel() {
    val people = repository.getPeople().cachedIn(viewModelScope)

    val favoritePeople = repository.getFavoritePeople().asFlow()

    fun favoritePerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.favoritePerson(person)
        }
    }

    fun unfavoritePerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.unfavoritePerson(person)
        }
    }
}