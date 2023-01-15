package com.berkan.starwarsapp.presentation.people_list

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkan.starwarsapp.domain.model.Person
import com.berkan.starwarsapp.domain.repository.StarWarsRepository
import com.berkan.starwarsapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleListViewModel @Inject constructor(
    private val repository: StarWarsRepository
) : ViewModel() {
    private val _listOfPeople = mutableStateListOf<Person>()

    val listOfPeople: List<Person>
        get() = _listOfPeople

    init {
        getPeople()
    }

    private fun getPeople() {
        viewModelScope.launch {
            when (val result = repository.getPeople()) {
                is Resource.Success -> {
                    result.data?.forEach { person ->
                        _listOfPeople.add(person)
                    }
                }
                is Resource.Error -> {

                }
            }
        }
    }
}