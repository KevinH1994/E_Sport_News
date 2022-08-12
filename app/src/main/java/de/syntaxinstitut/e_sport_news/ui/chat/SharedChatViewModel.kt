package de.syntaxinstitut.e_sport_news.ui.chat

import android.app.Application
import androidx.lifecycle.*
import de.syntaxinstitut.e_sport_news.R
import de.syntaxinstitut.e_sport_news.data.models.Chat.Contact
import de.syntaxinstitut.e_sport_news.data.models.Chat.Message
import de.syntaxinstitut.e_sport_news.data.models.Repository.ChatRepository
import de.syntaxinstitut.e_sport_news.data.models.database.getDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SharedChatViewModel(application: Application) : AndroidViewModel(application) { // Erstelle eine Instanz des Repository

    private val database = getDatabase(application)
    private val repository = ChatRepository(database)

    // In dieser Variable wird auf die Variable contacts aus dem Repository verwiesen

    private var _contacts = repository.contact
    val contacts: LiveData<List<Contact>>
        get() = _contacts

    // In dieser Variable wird der aktuelle Kontakt aus der _contacts Liste gespeichert
    private lateinit var _currentContact: Contact
    val currentContact: Contact
        get() = _currentContact

    // Diese Variable gibt an, ob bereits ein Nachricht Entwurf existiert oder nicht
    private var _draftMessageExist = false
    val draftMessageExist
        get() = _draftMessageExist

    // Diese Variable gibt an, ob der Chat geöffnet ist oder nicht

    private var _chatOpen = false
    val chatOpen
        get() = _chatOpen

    // In dieser Live Data Variablen wird durch Data Binding der aktuelle Input Text gespeichert

    var inputText = MutableLiveData<String>()

    lateinit var currentChatHistory: LiveData<List<Message>>
    lateinit var _localChatHistory: MutableList<Message>

    /**
     * Diese Funktion öffnet den Chat und setzt alle nötigen Variablen dementsprechend
     * @param contactIndex die Stelle des Kontakts in der contacts Liste
     */
    fun openChat(contactIndex: Int) {
        _currentContact = contacts.value!!.get(contactIndex)
        _chatOpen = true
        inputText.value = ""
        _draftMessageExist = false
        currentChatHistory = database.chatDatabaseDao.getAllMessageById(_currentContact.id)
    }

    /**
     * Diese Funktion schließt den Chat und setzt alle nötigen Variablen dementsprechend
     */
    fun closeChat() {
        _chatOpen = false
        _draftMessageExist = false
    }

    /**
     * Diese Funktion erstellt einen neuen Nachricht Entwurf und fügt dieses Message Objekt >an
     * erster Stelle< in die Liste ein. Sie setzt zudem alle benötigten Variablen dementsprechend
     */
    fun createNewDraftMessage() {
        _localChatHistory = currentChatHistory.value as MutableList
        val newDraft = Message(message = "", alpha = 0.3f, contactId = _currentContact.id)
        _localChatHistory.add(0, newDraft)
        _draftMessageExist = true
    }

    /**
     * Diese Funktion schreibt den übergebenen Text in die message Variable des ersten Message Objekts
     * @param text Der Text der in der neusten Nachricht stehen soll
     */
    fun writeInDraftMessage(text: String) {
        _localChatHistory[0].message = text
    }

    /**
     * Diese Funktion "schickt die Nachricht ab", indem die Transparenz des ersten Message Objekts
     * auf undurchsichtig gestellt wird. Sie setzt zudem alle benötigten Variablen dementsprechend
     */
    fun sendDraftMessage() {
        _localChatHistory[0].alpha = 1f
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database.chatDatabaseDao.insertMessage(_localChatHistory[0])
            }
        }

        _draftMessageExist = false
    }

    /**
     * Diese Funktion entfernt den Nachricht Entwurf, indem sie das neuste Message Objekt
     * aus der Liste entfernt. Sie setzt zudem alle benötigten Variablen dementsprechend
     */
    fun removeDraftMessage() {
        _localChatHistory.removeAt(0)
        _draftMessageExist = false
    }

    fun initalInsertContacts() {
        val init_contacts = listOf(

            Contact(name = "Faker", imageRes = R.drawable.faker),
            Contact(name = "Shroud", imageRes = R.drawable.shroud),
            Contact(name = "KayzahR", imageRes = R.drawable.kayzahr),
            Contact(name = "Elli", imageRes = R.drawable.elli),
            Contact(name = "Rekkles", imageRes = R.drawable.rekkles),
            Contact(name = "Caps", imageRes = R.drawable.caps),
            Contact(name = "Pengu", imageRes = R.drawable.pengu),
            Contact(name = "Broxah", imageRes = R.drawable.broxah),
            Contact(name = "Agurin", imageRes = R.drawable.agurin),
            Contact(name = "NoWay4u", imageRes = R.drawable.noway),
            Contact(name = "Broeki", imageRes = R.drawable.broeki),
            Contact(name = "Tolkin", imageRes = R.drawable.tolkin)

        )
        for (contact in init_contacts) {
            viewModelScope.launch {
                repository.insertContact(contact)
            }
        }
    }
}
