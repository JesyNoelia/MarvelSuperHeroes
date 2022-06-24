package com.jesy.marvelsuperheroes.domain.model

data class CharacterModel(
    val id: Int,
    val name: String,
    val thumbnail: String,
    val thumbnailExt: String,
    val description: String,
    val comics: List<String>
)
