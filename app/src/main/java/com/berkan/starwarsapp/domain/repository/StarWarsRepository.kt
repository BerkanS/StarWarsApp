package com.berkan.starwarsapp.domain.repository

import androidx.paging.PagingData
import com.berkan.starwarsapp.domain.model.Person
import kotlinx.coroutines.flow.Flow

interface StarWarsRepository {
    fun getPeople(): Flow<PagingData<Person>>
}