package de.syntaxinstitut.e_sport_news.data.models.Repository

import de.syntaxinstitut.e_sport_news.R
import de.syntaxinstitut.e_sport_news.data.models.Chat.Contact

class ChatRepository {


    private val _contact = loadContacts()
            val contact: List<Contact>
                get() = _contact






    private fun loadContacts(): List<Contact>{
        return listOf(

            Contact("Faker", R.drawable.faker, mutableListOf()),
            Contact("Shroud", R.drawable.shroud, mutableListOf()),
            Contact("KayzahR", R.drawable.kayzahr, mutableListOf()),
            Contact("Elli", R.drawable.elli, mutableListOf()),
            Contact("Rekkles", R.drawable.rekkles, mutableListOf()),
            Contact("Caps", R.drawable.caps, mutableListOf()),
            Contact("Pengu", R.drawable.pengu, mutableListOf()),
            Contact("Broxah", R.drawable.broxah, mutableListOf()),
            Contact("Agurin", R.drawable.agurin, mutableListOf()),
            Contact("NoWay4u", R.drawable.noway, mutableListOf()),
            Contact("Broeki", R.drawable.broeki, mutableListOf()),
            Contact("Tolkin", R.drawable.tolkin, mutableListOf())







        )
    }
}
