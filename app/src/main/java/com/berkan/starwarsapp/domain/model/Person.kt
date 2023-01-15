package com.berkan.starwarsapp.domain.model

import androidx.room.Entity
import com.squareup.moshi.Json

data class Person(
    val name: String,
    val height: String,
    val mass: String,
    val gender: String
)
