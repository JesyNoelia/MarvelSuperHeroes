package com.jesy.marvelsuperheroes.data.datasource.response

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXX>,
    val returned: Int
)