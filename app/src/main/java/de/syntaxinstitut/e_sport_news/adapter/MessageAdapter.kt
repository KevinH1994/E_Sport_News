package de.syntaxinstitut.e_sport_news.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.e_sport_news.R
import de.syntaxinstitut.e_sport_news.data.models.Chat.Message

class MessageAdapter(
    private val dataset: List<Message>,
    private val context: Context
) : RecyclerView.Adapter<MessageAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvText: TextView = itemView.findViewById(R.id.tvMessageText)
        val cvMessage: CardView = itemView.findViewById(R.id.cvMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_message, parent, false)

        return ItemViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val message = dataset[position]
        holder.tvText.text = message.message
        holder.cvMessage.alpha = message.alpha

        holder.cvMessage.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, message.message)
            startActivity(context, Intent.createChooser(shareIntent, null), null)

            true
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
