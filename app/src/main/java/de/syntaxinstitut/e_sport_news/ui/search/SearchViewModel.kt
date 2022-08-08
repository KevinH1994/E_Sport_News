package de.syntaxinstitut.e_sport_news.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.common.api.Api
import de.syntaxinstitut.e_sport_news.data.models.Repository.SearchRepository
import de.syntaxinstitut.e_sport_news.remote.SearchApi
import kotlinx.coroutines.launch

class SearchViewModel(application: Application): AndroidViewModel(application) {
    private val repository = SearchRepository(SearchApi)
    val result = repository.game
    var navigateToFragmentHomeFragment = MutableLiveData(false)



    fun loadData(name:String){
        viewModelScope.launch {
            repository.getGame(name)
        }
    }

    fun navigateToFragmentHome(){
        navigateToFragmentHomeFragment.value = true
    }


    fun resetAllValues(){
        navigateToFragmentHomeFragment.value = false
    }
}