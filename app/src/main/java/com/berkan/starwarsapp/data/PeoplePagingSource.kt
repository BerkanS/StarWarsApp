package com.berkan.starwarsapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.berkan.starwarsapp.data.remote.StarWarsApi
import com.berkan.starwarsapp.domain.model.Person

private const val STARTING_PAGE_INDEX = 1

class PeoplePagingSource(
    private val starWarsApi: StarWarsApi
) : PagingSource<Int, Person>() {

    override fun getRefreshKey(state: PagingState<Int, Person>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Person> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX

        return try {
            val people = starWarsApi.getPeople(page = pageIndex).people
            LoadResult.Page(
                data = people,
                prevKey = if (pageIndex == 1) null else pageIndex - 1,
                nextKey = if (people.isEmpty()) null else pageIndex + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}