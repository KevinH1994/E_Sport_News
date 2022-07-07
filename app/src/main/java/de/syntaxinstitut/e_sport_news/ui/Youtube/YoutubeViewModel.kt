package de.syntaxinstitut.e_sport_news.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntaxinstitut.e_sport_news.remote.YoutubeApi
import de.syntaxinstitut.e_sport_news.data.models.Repository.YoutubeRepsitory
import kotlinx.coroutines.launch
import java.lang.Exception

const val TAG ="YoutubeViewModel"

enum class ApiStatus{LOADING, ERROR, DONE}

class YoutubeViewModel: ViewModel() {
    private val repository = YoutubeRepsitory(YoutubeApi)

    private val _loading = MutableLiveData<ApiStatus>()
    val loading: LiveData<ApiStatus>
        get() = _loading

    val video = repository.videoList

    init {
        loadVideoData()
    }

    fun loadVideoData(){
        viewModelScope.launch {
            _loading.value = ApiStatus.LOADING
            try {
                repository.getVideo()
                _loading.value = ApiStatus.DONE
            }catch (e:Exception){
                Log.e(TAG, "Error loading Data from Api: $e")
                _loading.value = ApiStatus.ERROR
            }
        }
    }
}