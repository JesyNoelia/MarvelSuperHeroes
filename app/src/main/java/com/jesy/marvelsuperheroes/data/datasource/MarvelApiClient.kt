package com.jesy.marvelsuperheroes.data.datasource

import com.jesy.marvelsuperheroes.data.datasource.response.CharactersResponse
import com.jesy.marvelsuperheroes.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiClient {

    @GET("v1/public/characters")
    suspend fun getAllCharacters(
        @Query("apiKey") apiKey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("limit") limit: String = Constants.limit

    ): CharactersResponse
}