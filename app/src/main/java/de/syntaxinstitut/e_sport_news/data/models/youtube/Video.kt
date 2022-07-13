package de.syntaxinstitut.e_sport_news.data.models.youtube

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


data class Video(
    val channelId: String,
    val channelName: String,
    val description: String,
    val lengthText: String,
    val publishedTimeText:String,
    val thumbnails: List<Thumbnails>,
    val title: String,
    val videoId: String,
    val viewCountText: String

)