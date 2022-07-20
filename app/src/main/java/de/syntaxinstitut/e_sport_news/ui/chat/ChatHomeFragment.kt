package de.syntaxinstitut.e_sport_news.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import de.syntaxinstitut.e_sport_news.adapter.ContactAdapter
import de.syntaxinstitut.e_sport_news.data.models.Repository.ChatRepository

import de.syntaxinstitut.e_sport_news.databinding.FragmentHomeBinding


class ChatHomeFragment: Fragment() {

    private val viewModel : SharedChatViewModel by viewModels()
    // Das binding für das QuizFragment wird deklariert
    private lateinit var binding: FragmentHomeBinding

    /**
     * Lifecycle Funktion onCreateView
     * Hier wird das binding initialisiert und das Layout gebaut
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Lifecycle Funktion onViewCreated
     * Hier werden die Elemente eingerichtet und z.B. onClickListener gesetzt
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Weise das viewModel und den lifecycleOwner zu

        binding. = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        // Weise der RecylerView einen ContactAdapter zu,
        // übergib dem Adapter die Contact Liste aus dem ViewModel

        val vacations = ChatRepository().contact
        val recyclerView = binding.rvContacts
        recyclerView.adapter = ContactAdapter(vacations)



        // Falls bereits ein Nachricht Entwurf existiert,
        // soll dieser mithilfe der Funktion aus dem ViewModel entfernt werden
        if (viewModel.draftMessageExist) {
            viewModel.removeDraftMessage()
        }

        // Falls der Chat bereits geöffnet ist,
        // soll dieser mithilfe der Funktion aus dem ViewModel geschlossen werden
        if (viewModel.chatOpen) {
            viewModel.closeChat()
        }
    }

}