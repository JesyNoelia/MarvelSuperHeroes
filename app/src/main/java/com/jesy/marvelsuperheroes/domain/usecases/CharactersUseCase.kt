package com.jesy.marvelsuperheroes.domain.usecases

import com.jesy.marvelsuperheroes.domain.model.CharacterModel
import com.jesy.marvelsuperheroes.domain.repository.MarvelRepository
import com.jesy.marvelsuperheroes.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharactersUseCase @Inject constructor(
    private val repository: MarvelRepository
){

    operator fun invoke(): Flow<Response<List<CharacterModel>>> = flow{
        try {
            emit(Response.Loading<List<CharacterModel>>())
            val list = repository.getAllCharacters().data.results.map{
                it.toCharacter()
            }
            emit(Response.Success<List<CharacterModel>>(list))
        }
        catch (e: HttpException){
            emit(Response.Error<List<CharacterModel>>(e.printStackTrace().toString()))

        }
        catch (e: IOException){
            emit(Response.Error<List<CharacterModel>>(e.printStackTrace().toString()))
        }
    }
}