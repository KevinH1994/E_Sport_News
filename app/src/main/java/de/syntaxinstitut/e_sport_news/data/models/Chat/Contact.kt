package de.syntaxinstitut.e_sport_news.data.models.Chat

import android.service.autofill.FillEventHistory

data class Contact(
    val name : String,
    val imageRes: Int,
    val chatHistory: MutableList<Message>
)
