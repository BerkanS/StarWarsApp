package com.berkan.starwarsapp.di

import com.berkan.starwarsapp.data.repository.DefaultStarWarsRepository
import com.berkan.starwarsapp.domain.repository.StarWarsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(defaultStarWarsRepository: DefaultStarWarsRepository): StarWarsRepository

}