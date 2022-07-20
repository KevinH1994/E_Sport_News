package de.syntaxinstitut.e_sport_news.data.models.Repository

import de.syntaxinstitut.e_sport_news.data.models.Chat.Contact

class ChatRepository {


    private val _contact = loadContacts()
            val contact: List<Contact>
                get() = _contact






    private fun loadContacts(): List<Contact>{
        return listOf(









        )
    }
}
