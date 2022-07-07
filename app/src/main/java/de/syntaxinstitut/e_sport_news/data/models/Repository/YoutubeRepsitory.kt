package de.syntaxinstitut.e_sport_news.data.models.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.syntaxinstitut.e_sport_news.data.models.youtube.Content
import de.syntaxinstitut.e_sport_news.remote.YoutubeApi


class YoutubeRepsitory(private val api: YoutubeApi) {

    private val _videoList = MutableLiveData<List<Content>>()
        val videoList: LiveData<List<Content>>
            get() = _videoList


    suspend fun getVideo(){
        _videoList.value = api.retrofitService.getVideo().contents
    }
}