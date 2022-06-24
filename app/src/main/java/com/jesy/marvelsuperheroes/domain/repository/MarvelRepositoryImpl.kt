package com.jesy.marvelsuperheroes.domain.repository

import com.jesy.marvelsuperheroes.data.datasource.MarvelApiClient
import com.jesy.marvelsuperheroes.data.datasource.response.CharactersResponse
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val api: MarvelApiClient
): MarvelRepository{
    override suspend fun getAllCharacters(): CharactersResponse {
        return api.getAllCharacters()
    }
}