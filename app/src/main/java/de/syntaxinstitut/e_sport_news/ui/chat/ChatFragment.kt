package de.syntaxinstitut.e_sport_news.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import de.syntaxinstitut.e_sport_news.adapter.MessageAdapter
import de.syntaxinstitut.e_sport_news.databinding.FragmentChatBinding

class ChatFragment: Fragment() {
    private var contactIndex: Int = 0

    private val viewModel: SharedChatViewModel by activityViewModels()

    private lateinit var binding: FragmentChatBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container,false)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        arguments?.let {
            contactIndex = it.getInt("contactIndex")
        }
        viewModel.openChat(contactIndex)

        binding.ivContactPicture.setImageResource(viewModel.currentContact.imageRes)

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


