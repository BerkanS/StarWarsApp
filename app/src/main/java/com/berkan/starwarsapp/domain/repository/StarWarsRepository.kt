package com.berkan.starwarsapp.domain.repository

import com.berkan.starwarsapp.domain.model.Person
import com.berkan.starwarsapp.domain.util.Resource

interface StarWarsRepository {
    suspend fun getPeople(): Resource<List<Person>>
}