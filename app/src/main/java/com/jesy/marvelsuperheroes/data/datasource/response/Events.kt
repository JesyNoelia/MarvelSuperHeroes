package com.jesy.marvelsuperheroes.data.datasource.response

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)