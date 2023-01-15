package com.berkan.starwarsapp.data.remote

import com.berkan.starwarsapp.domain.model.PeopleResponse
import com.berkan.starwarsapp.domain.model.Person
import com.berkan.starwarsapp.domain.util.Resource
import retrofit2.http.GET

interface StarWarsApi {
    @GET("people")
    suspend fun getPeople(): PeopleResponse
}