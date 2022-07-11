package de.syntaxinstitut.e_sport_news.data.models.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.syntaxinstitut.e_sport_news.data.models.youtube.Content
import de.syntaxinstitut.e_sport_news.remote.YoutubeApi

const val TAG = "AppRepositoryTAG"


class YoutubeRepsitory(private val api: YoutubeApi) {

    private val _videoList = MutableLiveData<List<Content>>()
        val videoList: LiveData<List<Content>>
            get() = _videoList


    suspend fun getVideo() {
        try {
            _videoList.value = api.retrofitService.getVideo().contents
        }catch (e:Exception){
            Log.e(TAG,"Error putting Data on API $e")
        }

    }
}