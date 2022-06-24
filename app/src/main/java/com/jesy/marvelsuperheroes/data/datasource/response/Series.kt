package com.jesy.marvelsuperheroes.data.datasource.response

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)