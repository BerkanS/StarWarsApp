package com.berkan.starwarsapp.di

import android.content.Context
import androidx.room.Room
import com.berkan.starwarsapp.BuildConfig.BASE_URL
import com.berkan.starwarsapp.data.local.StarWarsDatabase
import com.berkan.starwarsapp.data.remote.StarWarsApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

private const val DB_NAME = "sw_db"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesOkHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun providesMoshi() =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Singleton
    @Provides
    fun providesApiService(moshi: Moshi,okHttpClient: OkHttpClient): StarWarsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create()
    }

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