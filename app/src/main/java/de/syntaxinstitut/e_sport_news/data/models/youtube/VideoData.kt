package de.syntaxinstitut.e_sport_news.data.models.youtube

data class VideoData(
    val title:String,
    val videoId: String,
    val author : AuthorData,
    val thumbnails : List<Thumbnail>
)
