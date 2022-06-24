package com.jesy.marvelsuperheroes.di

import com.jesy.marvelsuperheroes.data.datasource.MarvelApiClient
import com.jesy.marvelsuperheroes.domain.repository.MarvelRepository
import com.jesy.marvelsuperheroes.domain.repository.MarvelRepositoryImpl
import com.jesy.marvelsuperheroes.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Appmodule {

    @Provides
    @Singleton
    fun provideMarvelApi(): MarvelApiClient{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideMarvelRepository(api: MarvelApiClient): MarvelRepository{
        return MarvelRepositoryImpl(api)
    }
}