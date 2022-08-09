package de.syntaxinstitut.e_sport_news.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import de.syntaxinstitut.e_sport_news.R
import de.syntaxinstitut.e_sport_news.data.models.Chat.Message
import de.syntaxinstitut.e_sport_news.data.models.news.News

class NewsAdapter(
private val dataset: List<News>,
private val context: Context
) : RecyclerView.Adapter<NewsAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitel: TextView = itemView.findViewById(R.id.tvTitle)
        val tvContent : TextView = itemView.findViewById(R.id.tv_content)
        val imageView : ImageView = itemView.findViewById(R.id.ivPicture)
        val cl : ConstraintLayout = itemView.findViewById(R.id.cl_news)
        var open :Boolean = false

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_news, parent, false)

        return ItemViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val news = dataset[position]
        holder.tvTitel.text = news.titel
        holder.tvContent.text = news.description
        holder.imageView.load(news.thumnail)


        holder.cl.setOnClickListener {
            if (holder.open){
                holder.tvContent.visibility = View.GONE
                holder.open = false
            }else {
                holder.tvContent.visibility = View.VISIBLE
                holder.open= true
            }
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}