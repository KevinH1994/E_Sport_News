package de.syntaxinstitut.e_sport_news.ui.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.syntaxinstitut.e_sport_news.data.models.Chat.Contact
import de.syntaxinstitut.e_sport_news.data.models.Chat.Message
import de.syntaxinstitut.e_sport_news.data.models.Repository.ChatRepository

class SharedChatViewModel : ViewModel() {    // Erstelle eine Instanz des Repository

    private val repository = ChatRepository()

    // In dieser Variable wird auf die Variable contacts aus dem Repository verwiesen


    private var _contacts = repository.contact
    val contacts: List<Contact>
        get() = _contacts

    // In dieser Variable wird der aktuelle Kontakt aus der _contacts Liste gespeichert
    private lateinit var _currentContact : Contact
    val currentContact : Contact
        get() = _currentContact



    // Diese Variable gibt an, ob bereits ein Nachricht Entwurf existiert oder nicht
    private var _draftMessageExist  = false
    val draftMessageExist
        get() = _draftMessageExist

    // Diese Variable gibt an, ob der Chat geöffnet ist oder nicht

    private var _chatOpen = false
    val chatOpen
        get() = _chatOpen

    // In dieser Live Data Variablen wird durch Data Binding der aktuelle Input Text gespeichert

    var inputText = MutableLiveData<String>()








    /**
     * Diese Funktion öffnet den Chat und setzt alle nötigen Variablen dementsprechend
     * @param contactIndex die Stelle des Kontakts in der contacts Liste
     */
    fun openChat(contactIndex: Int) {
        _currentContact= contacts[contactIndex]
        _chatOpen = true
        inputText.value = ""
        _draftMessageExist = false
    }

    /**
     * Diese Funktion schließt den Chat und setzt alle nötigen Variablen dementsprechend
     */
    fun closeChat() {
        _chatOpen= false
        _draftMessageExist = false

    }

    /**
     * Diese Funktion erstellt einen neuen Nachricht Entwurf und fügt dieses Message Objekt >an
     * erster Stelle< in die Liste ein. Sie setzt zudem alle benötigten Variablen dementsprechend
     */
    fun createNewDraftMessage() {
        val newDraft = Message("", 0.3f)
        _currentContact.chatHistory.add(0, newDraft)
        _draftMessageExist = true
    }

    /**
     * Diese Funktion schreibt den übergebenen Text in die message Variable des ersten Message Objekts
     * @param text Der Text der in der neusten Nachricht stehen soll
     */
    fun writeInDraftMessage(text: String) {
        _currentContact.chatHistory[0].message = text
    }

    /**
     * Diese Funktion "schickt die Nachricht ab", indem die Transparenz des ersten Message Objekts
     * auf undurchsichtig gestellt wird. Sie setzt zudem alle benötigten Variablen dementsprechend
     */
    fun sendDraftMessage() {
        _currentContact.chatHistory[0].alpha = 1f
        _draftMessageExist = false
    }

    /**
     * Diese Funktion entfernt den Nachricht Entwurf, indem sie das neuste Message Objekt
     * aus der Liste entfernt. Sie setzt zudem alle benötigten Variablen dementsprechend
     */
    fun removeDraftMessage() {
        _currentContact.chatHistory.removeAt(0)
        _draftMessageExist = false
    }
}