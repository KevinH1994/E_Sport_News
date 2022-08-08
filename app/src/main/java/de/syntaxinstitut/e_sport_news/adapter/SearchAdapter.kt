package de.syntaxinstitut.e_sport_news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.e_sport_news.R
import de.syntaxinstitut.e_sport_news.data.models.suche.GameResult

class SearchAdapter(
    private val dataset: List<GameResult>
): RecyclerView.Adapter<SearchAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tv: TextView = itemView.findViewById(R.id.game_name2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_result,parent, false)
        return ItemViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val result = dataset[position]
        holder.tv.text = result.name
    }

    override fun getItemCount(): Int {
        return dataset.size

    }
}
