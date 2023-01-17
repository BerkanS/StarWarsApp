package com.berkan.starwarsapp.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.berkan.starwarsapp.data.remote.StarWarsApi
import com.berkan.starwarsapp.data.repository.StarWarsRepository
import com.berkan.starwarsapp.domain.model.Fakes
import com.berkan.starwarsapp.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class StarWarsRepositoryTest {

    private lateinit var database: StarWarsDatabase
    private lateinit var repository: StarWarsRepository
    private lateinit var dao: FavoritesDao

    private val starWarsApi = mockk<StarWarsApi>(relaxed = true)

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            StarWarsDatabase::class.java,
        ).allowMainThreadQueries().build()

        dao = database.favoritesDao()
        repository = StarWarsRepository(starWarsApi, dao)
    }

    @Test
    fun testFavoritePersonSuccess() = runTest {
        repository.favoritePerson(Fakes.personOne)

        val allPeople = repository.getFavoritePeople().getOrAwaitValue()

        assertThat(allPeople).contains(Fakes.personOne)
    }

    @Test
    fun testDeletePersonSuccess() = runTest {
        listOf(Fakes.personOne, Fakes.personTwo, Fakes.personThree).forEach { person ->
            repository.favoritePerson(person)
        }

        repository.unfavoritePerson(Fakes.personOne)

        val allPeople = repository.getFavoritePeople().getOrAwaitValue()

        assertThat(allPeople).doesNotContain(Fakes.personOne)
    }

    @After
    fun cleanup() {
        database.close()
    }

}