package com.berkan.starwarsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.berkan.starwarsapp.data.PeoplePagingSource
import com.berkan.starwarsapp.data.local.FavoritesDao
import com.berkan.starwarsapp.data.remote.StarWarsApi
import com.berkan.starwarsapp.domain.model.Person
import com.berkan.starwarsapp.domain.repository.StarWarsRepository
import javax.inject.Inject

private const val NETWORK_PAGE_SIZE = 10

class DefaultStarWarsRepository @Inject constructor(
    private val remote: StarWarsApi,
    private val local: FavoritesDao
) : StarWarsRepository {
    override fun getPeople() = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE
        ),
        pagingSourceFactory = {
            PeoplePagingSource(remote)
        }
    ).flow

    override fun getFavoritePeople() = local.getPeople()

    override suspend fun favoritePerson(person: Person) = local.insertPerson(person)

    override suspend fun unfavoritePerson(person: Person) = local.deletePerson(person)


}