package com.jesy.marvelsuperheroes.ui.CharactersList

import com.jesy.marvelsuperheroes.domain.model.CharacterModel

class MarvelListState (
    val isLoading: Boolean = false,
    val characterList: List<CharacterModel>? = emptyList(),
    val error: String = ""
    //val error: Error? = null
)