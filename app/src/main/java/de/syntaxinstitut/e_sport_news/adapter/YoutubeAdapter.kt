package de.syntaxinstitut.e_sport_news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import de.syntaxinstitut.e_sport_news.R
import de.syntaxinstitut.e_sport_news.data.models.youtube.ContentsData
import de.syntaxinstitut.e_sport_news.data.models.youtube.ResponseData


class YoutubeAdapter(
    private val dataset : List<ContentsData>
) : RecyclerView.Adapter<YoutubeAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivVideo : YouTubePlayerView = itemView.findViewById(R.id.yt_vv)
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
        val urlThumbnail = video.video!!.thumbnails.first()





        val urlVideo = "https://www.youtube.com/watch?v="+video.video!!.videoId

        val videoUri = urlVideo.toUri().buildUpon().scheme("https").build()

        holder.ivVideo.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.cueVideo(video.video!!.videoId, 0f)
            }
        })




        holder.tvTitel.text = video.video.title
        holder.tvchannel.text = video.video.author.title


    }
    override fun getItemCount(): Int {
        return dataset.size
    }
}
