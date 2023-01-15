package com.berkan.starwarsapp.data.repository

import com.berkan.starwarsapp.data.remote.StarWarsApi
import com.berkan.starwarsapp.domain.model.Person
import com.berkan.starwarsapp.domain.repository.StarWarsRepository
import com.berkan.starwarsapp.domain.util.Resource
import javax.inject.Inject

class DefaultStarWarsRepository @Inject constructor(
    private val starWarsApi: StarWarsApi
) : StarWarsRepository {
    override suspend fun getPeople(): Resource<List<Person>> {
        return try {
            Resource.Success(
                data = starWarsApi.getPeople().people
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.Error(e.message ?: "Unknown error occurred.")
        }
    }
}