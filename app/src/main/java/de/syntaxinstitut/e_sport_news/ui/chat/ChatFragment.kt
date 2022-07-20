package de.syntaxinstitut.e_sport_news.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import de.syntaxinstitut.e_sport_news.adapter.MessageAdapter
import de.syntaxinstitut.e_sport_news.databinding.FragmentChatBinding

class ChatFragment: Fragment() {
    private var contactIndex: Int = 0

    private val viewModel: SharedChatViewModel by viewModels()

    private lateinit var binding: FragmentChatBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.viewmodelChat = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        arguments?.let {
            contactIndex = it.getInt("contactIndex")
        }
        viewModel.openChat(contactIndex)


        val chatHistory = viewModel.currentContact.chatHistory
        val recyclerView = binding.rvMessages

        val messageAdapter = MessageAdapter(chatHistory,requireContext())
        recyclerView.adapter = messageAdapter


        viewModel.inputText.observe(viewLifecycleOwner){
            if (viewModel.draftMessageExist == true){
                if (it != ""){
                    viewModel.writeInDraftMessage(it)
                    messageAdapter.notifyItemChanged(0,Unit)

                }else{
                    viewModel.removeDraftMessage()
                    messageAdapter.notifyItemRemoved(0)
                }
            }else{
                if (it !=""){
                    viewModel.createNewDraftMessage()
                    viewModel.writeInDraftMessage(it)
                    messageAdapter.notifyItemInserted(0)
                    recyclerView.scrollToPosition(0)
                }
            }
        }
        binding.btnSend.setOnClickListener {
            viewModel.sendDraftMessage()
            messageAdapter.notifyItemChanged(0,Unit)
            binding.textInput.setText("")
        }
        binding.btnBack.setOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
    }

}


