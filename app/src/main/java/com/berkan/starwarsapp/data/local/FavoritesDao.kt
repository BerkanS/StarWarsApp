package com.berkan.starwarsapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.berkan.starwarsapp.domain.model.Person

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM person")
    fun getPeople(): LiveData<List<Person>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)
}