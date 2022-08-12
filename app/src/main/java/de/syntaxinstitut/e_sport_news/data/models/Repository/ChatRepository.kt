package de.syntaxinstitut.e_sport_news.data.models.Repository

import androidx.lifecycle.LiveData
import de.syntaxinstitut.e_sport_news.data.models.Chat.Contact
import de.syntaxinstitut.e_sport_news.data.models.database.ChatDatabase

class ChatRepository(private val database: ChatDatabase) {

    private val _contact = loadContacts()
    val contact: LiveData<List<Contact>>
        get() = _contact

    private fun loadContacts(): LiveData<List<Contact>> {

        return database.chatDatabaseDao.getAllContact()

        /** return **/
    }

    suspend fun insertContact(contact: Contact) {
        database.chatDatabaseDao.insertContact(contact)
    }
}
