package de.syntaxinstitut.e_sport_news.data.models.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.syntaxinstitut.e_sport_news.data.models.suche.GameResult
import de.syntaxinstitut.e_sport_news.remote.SearchApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.http.Body


const val Toast = "AppRepository"

class SearchRepository(private val api : SearchApi) {

    private val _game = MutableLiveData<List<GameResult>>()
        val game: LiveData<List<GameResult>>
            get() = _game




    suspend fun getGame(name: String){
        try {
            val mediaType = "text/plain; charset=utf-8".toMediaType()
            val fields = "fields *; search \"${name}\";".toRequestBody(mediaType)
            _game.value = api.retrofitService.getGames(fields)
        }catch (e:Exception) {
            Log.e(Toast, "Error putting Data on API:$e")
        }
    }
}