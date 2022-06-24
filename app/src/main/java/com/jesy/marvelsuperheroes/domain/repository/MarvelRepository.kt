package com.jesy.marvelsuperheroes.domain.repository

import com.jesy.marvelsuperheroes.data.datasource.response.CharactersResponse

interface MarvelRepository {

    suspend fun getAllCharacters() : CharactersResponse
}