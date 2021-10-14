package com.tempo.tempotask.data.model

data class News(
    val status: String,
    var totalResults: Int,
    var articles: List<Articles>
)


