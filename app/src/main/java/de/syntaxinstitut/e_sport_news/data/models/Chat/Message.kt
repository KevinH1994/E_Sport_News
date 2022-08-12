package de.syntaxinstitut.e_sport_news.data.models.Chat

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Contact::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("contactId"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class Message(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var message: String,
    var alpha: Float,
    var contactId: Long,
)
