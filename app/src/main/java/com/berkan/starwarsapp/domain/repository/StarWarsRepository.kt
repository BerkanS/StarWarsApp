package com.berkan.starwarsapp.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.berkan.starwarsapp.domain.model.Person
import kotlinx.coroutines.flow.Flow

interface StarWarsRepository {
    fun getPeople(): Flow<PagingData<Person>>

    fun getFavoritePeople(): LiveData<List<Person>>

    suspend fun favoritePerson(person: Person)

    suspend fun unfavoritePerson(person: Person)
}