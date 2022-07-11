package de.syntaxinstitut.e_sport_news.adapter

import android.service.autofill.Dataset
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.VideoView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.e_sport_news.R
import de.syntaxinstitut.e_sport_news.data.models.youtube.Content
import de.syntaxinstitut.e_sport_news.data.models.youtube.Video


class YoutubeAdapter(
    private val dataset: List<Content>
): RecyclerView.Adapter<YoutubeAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val vVideo: VideoView = itemView.findViewById(R.id.vv_youtube1)
        val tvTitel: TextView = itemView.findViewById(R.id.tv_titel)
        val tvchannel :TextView = itemView.findViewById(R.id.tv_channelName)


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val itemLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_youtube,parent,false)
        return ItemViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // holt das videoItem aus dem Dataset
        val video = dataset[position]

        //val url = "https://www.youtube.com/watch?v=fhz_vLtEiTY&"

        val videoUri = video.video.videoId.toUri().buildUpon().scheme("https").build()

        holder.vVideo.setVideoURI(videoUri)
        holder.vVideo.start()

        holder.tvTitel.text = video.video.title

        holder.tvchannel.text = video.video.channelName
    }
    override fun getItemCount(): Int {
        return dataset.size
    }
}
