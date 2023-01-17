package com.berkan.starwarsapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi

@Entity
@JsonClass(generateAdapter = true)
data class Person(
    @PrimaryKey
    val name: String,
    val height: String,
    val mass: String,
    @Json(name = "hair_color")
    val hairColor: String,
    @Json(name = "skin_color")
    val skinColor: String,
    @Json(name = "eye_color")
    val eyeColor: String,
    @Json(name = "birth_year")
    val birthYear: String,
    val gender: String
)

fun Person.toJson(): String {
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter(Person::class.java).lenient()
    return jsonAdapter.toJson(this)
}

fun String.toPerson(): Person? {
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter(Person::class.java).lenient()
    return jsonAdapter.fromJson(this)

}
