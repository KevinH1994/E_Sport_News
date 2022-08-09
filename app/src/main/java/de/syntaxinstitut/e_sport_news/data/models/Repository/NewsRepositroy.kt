package de.syntaxinstitut.e_sport_news.data.models.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.syntaxinstitut.e_sport_news.data.models.news.News
import de.syntaxinstitut.e_sport_news.data.models.news.NewsList
import de.syntaxinstitut.e_sport_news.remote.NewsApi

const val TAGNEWS= "AppRepository"

class NewsRepositroy(private val api : NewsApi) {


    private val _news = MutableLiveData<NewsList>()
        val news : LiveData<NewsList>
            get() = _news



    suspend fun getNews(){
        try {
            _news.value = api.retrofitService.getNews()
        }catch (e:Exception){
            Log.e(TAGNEWS, "Error puttingen Data on API $e")
        }
    }
}