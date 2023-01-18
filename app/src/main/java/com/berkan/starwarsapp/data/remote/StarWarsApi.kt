package com.berkan.starwarsapp.data.remote

import com.berkan.starwarsapp.domain.model.PeopleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsApi {
    @GET("people")
    suspend fun getPeople(@Query("page") page: Int): PeopleResponse
}