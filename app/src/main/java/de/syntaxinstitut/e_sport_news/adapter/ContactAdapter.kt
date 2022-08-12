package de.syntaxinstitut.e_sport_news.adapter

import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.e_sport_news.R
import de.syntaxinstitut.e_sport_news.data.models.Chat.Contact
import de.syntaxinstitut.e_sport_news.ui.chat.ChatHomeFragment
import de.syntaxinstitut.e_sport_news.ui.chat.ChatHomeFragmentDirections

class ContactAdapter(
    private val dataset: List<Contact>
): RecyclerView.Adapter<ContactAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView){
        val ivPicture: ImageView = itemView.findViewById(R.id.ivContactPicture)
        val tvName : TextView= itemView.findViewById(R.id.tvContactName)
        val tvLastMessage : TextView = itemView.findViewById(R.id.tvContactLastMessage)
        val clContact: ConstraintLayout = itemView.findViewById(R.id.clContact)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_contact, parent,false)
        return ItemViewHolder(itemLayout)


    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item= dataset[position]
        holder .ivPicture.setImageResource(item.imageRes)


        holder.tvName.text = item.name
        if(!item.chatHistory.isEmpty()) {
            holder.tvLastMessage.text = item.chatHistory.last().message
        }else {
            holder.tvLastMessage.text = ""
        }

        holder.clContact.setOnClickListener {
            holder.itemView.findNavController()
                .navigate(ChatHomeFragmentDirections.actionChatHomeFragmentToChatFragment2(contactIndex = position , contactImage = item.imageRes ))
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}