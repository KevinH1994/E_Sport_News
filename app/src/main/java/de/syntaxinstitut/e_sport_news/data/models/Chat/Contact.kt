package de.syntaxinstitut.e_sport_news.data.models.Chat

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val imageRes: Int,
)
