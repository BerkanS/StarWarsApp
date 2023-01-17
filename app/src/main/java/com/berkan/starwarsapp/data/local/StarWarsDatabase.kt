package com.berkan.starwarsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.berkan.starwarsapp.domain.model.Person

@Database(
    entities = [
        Person::class
    ], version = 1
)
abstract class StarWarsDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}