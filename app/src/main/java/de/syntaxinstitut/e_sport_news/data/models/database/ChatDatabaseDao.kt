package de.syntaxinstitut.e_sport_news.data.models.database

import androidx.lifecycle.LiveData
import androidx.room.*
import de.syntaxinstitut.e_sport_news.data.models.Chat.Contact
import de.syntaxinstitut.e_sport_news.data.models.Chat.Message

@Dao
interface ChatDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: Message): Long

    @Update
    suspend fun updateContact(contact: Contact)

    @Query("SELECT * FROM Contact")
    fun getAllContact(): LiveData<List<Contact>>

    @Query("SELECT * FROM Message WHERE contactId = :contactId")
    fun getAllMessageById(contactId: Long): LiveData<List<Message>>
}
