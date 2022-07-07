package de.syntaxinstitut.e_sport_news.adapter

import android.service.autofill.Dataset
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.e_sport_news.R
import de.syntaxinstitut.e_sport_news.data.models.youtube.Content

class YoutubeAdapter(
    private val dataset: List<Content>
): RecyclerView.Adapter<YoutubeAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val vVideo: VideoView = itemView.findViewById(R.id.vv_youtube1)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val itemLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_youtube,parent,false)
        return ItemViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // holt das videoItem aus dem Dataset
        val video = dataset[position]

        val videoUri = video.video.videoId.toUri().buildUpon().scheme("https").build()

        holder.vVideo.load()
    }
}