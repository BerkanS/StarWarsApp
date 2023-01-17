package com.berkan.starwarsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.berkan.starwarsapp.data.PeoplePagingSource
import com.berkan.starwarsapp.data.local.FavoritesDao
import com.berkan.starwarsapp.data.remote.StarWarsApi
import com.berkan.starwarsapp.domain.model.Person
import javax.inject.Inject

private const val NETWORK_PAGE_SIZE = 10

class StarWarsRepository @Inject constructor(
    private val remote: StarWarsApi,
    private val local: FavoritesDao
) {
    fun getPeople() = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE
        ),
        pagingSourceFactory = {
            PeoplePagingSource(remote)
        }
    ).flow

    fun getFavoritePeople() = local.getPeople()

    suspend fun favoritePerson(person: Person) = local.insertPerson(person)

    suspend fun unfavoritePerson(person: Person) = local.deletePerson(person)


}