package de.syntaxinstitut.e_sport_news.data.models.youtube

data class ResponseData(
    val contents: MutableList<ContentsData>,
    val cursorNext: String
)
