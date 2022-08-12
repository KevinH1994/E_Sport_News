package de.syntaxinstitut.e_sport_news.data.models.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.syntaxinstitut.e_sport_news.data.models.Chat.Contact
import de.syntaxinstitut.e_sport_news.data.models.Chat.Message

@Database(entities = [Contact::class, Message::class,], version = 1)
abstract class ChatDatabase : RoomDatabase() {
    abstract val chatDatabaseDao: ChatDatabaseDao
}

private lateinit var INSTANCE: ChatDatabase

@Synchronized
fun getDatabase(context: Context): ChatDatabase {

    if (!::INSTANCE.isInitialized) {
        INSTANCE = Room.databaseBuilder(
            context.applicationContext,
            ChatDatabase::class.java,
            "notizy_database"
        ).build()
    }

    return INSTANCE
}
