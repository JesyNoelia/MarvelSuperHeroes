package com.jesy.marvelsuperheroes.data.datasource.response

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)