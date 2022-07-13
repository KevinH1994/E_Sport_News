package de.syntaxinstitut.e_sport_news.data.models.youtube

data class ResponseData(
    val contents: List<Video>,
    val estimatedResults: String,
    val next : String
)
