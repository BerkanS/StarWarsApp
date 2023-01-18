package com.berkan.starwarsapp.di

import android.content.Context
import androidx.room.Room
import com.berkan.starwarsapp.data.local.StarWarsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DB_NAME = "sw_db"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDao(db: StarWarsDatabase) = db.favoritesDao()

    @Singleton
    @Provides
    fun providesStarWarsDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(
            app,
            StarWarsDatabase::class.java,
            DB_NAME
        ).build()
}