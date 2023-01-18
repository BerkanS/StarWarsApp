package com.berkan.starwarsapp.presentation.people_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.berkan.starwarsapp.domain.repository.StarWarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeopleListViewModel @Inject constructor(
    private val repository: StarWarsRepository
) : ViewModel() {

    fun getPeople() = repository.getPeople().cachedIn(viewModelScope)
}