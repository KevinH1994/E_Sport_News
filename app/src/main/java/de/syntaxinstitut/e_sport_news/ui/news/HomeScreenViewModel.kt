package de.syntaxinstitut.e_sport_news.ui.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import de.syntaxinstitut.e_sport_news.adapter.NewsAdapter
import de.syntaxinstitut.e_sport_news.data.models.Repository.NewsRepositroy
import de.syntaxinstitut.e_sport_news.remote.NewsApi
import kotlinx.coroutines.launch

/**
 * Das ViewModel des One Fragments
 */
class HomeScreenViewModel(application: Application) : AndroidViewModel(application) {
    val repository = NewsRepositroy(NewsApi)
    val news = repository.news


    fun loadNews(){
        viewModelScope.launch {
            repository.getNews()
        }
    }
}
