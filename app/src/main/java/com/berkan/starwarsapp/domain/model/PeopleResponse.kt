package com.berkan.starwarsapp.domain.model

import com.squareup.moshi.Json

data class PeopleResponse(
    @Json(name = "results")
    val people: List<Person>,
    val count: Int
)