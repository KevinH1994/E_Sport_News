package de.syntaxinstitut.e_sport_news.data.models.youtube

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Video(
    @PrimaryKey
    @Json(name ="")
    val channelName: String,
    val description: String,
    val lengthText: String,
    @Json(name= "")
    val title: String,
    @Json(name = "")
    val videoId: String,
)