package de.syntaxinstitut.e_sport_news.data.models.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.syntaxinstitut.e_sport_news.data.models.youtube.ContentsData
import de.syntaxinstitut.e_sport_news.data.models.youtube.ResponseData
import de.syntaxinstitut.e_sport_news.remote.YoutubeApi

const val TAG = "AppRepositoryTAG"


class YoutubeRepsitory(private val api: YoutubeApi) {

    private var _videoList = MutableLiveData<ResponseData>()
        val videoList: LiveData<ResponseData>
            get() = _videoList


    suspend fun getVideo() {
        try {
          val responseData = ResponseData(mutableListOf<ContentsData>(),"")
            for (item in api.retrofitService.getVideo().contents){
                if (item.type == "video"){
                    responseData.contents.add(item)
                }
            }
            _videoList.value = responseData
        }catch (e:Exception){
            Log.e(TAG,"Error putting Data on API $e")
        }

    }
}