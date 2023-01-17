package com.berkan.starwarsapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.berkan.starwarsapp.domain.model.Person

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM person")
    fun getPeople(): LiveData<List<Person>>

    @Insert
    suspend fun insertPerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)
}