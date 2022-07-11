package de.syntaxinstitut.e_sport_news.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.syntaxinstitut.e_sport_news.data.models.youtube.Content
import de.syntaxinstitut.e_sport_news.data.models.youtube.Youtube
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

// Die Konstante enthlt die URL der Api

const val BASE_URL ="https://youtube-search-and-download.p.rapidapi.com/"

val client :  OkHttpClient= OkHttpClient.Builder().addInterceptor(){    chain ->
    val request = Request.Builder()
        .addHeader("X-RapidAPI-Key", "d4fd2ef3c4msh75966aa06d019bcp198c28jsncf949f7fbaa3")
        .addHeader("X-RapidAPI-Host", "youtube-search-and-download.p.rapidapi.com")
        .build()
    chain.proceed(request)

}.build()



//Moshi konventiert Serverantworten in Kotlin

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//Retrofit übernimmt die Kommunikation und übersetzt die Antwort

private val retrofit = Retrofit.Builder()
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

//Das Interface bestimmt, wie mit dem Server kommuniziert wird

interface YoutubeApiService{
    @GET("search?query=e-sport-news")
    suspend fun getVideo(): Youtube

}
object YoutubeApi{
    val retrofitService: YoutubeApiService by lazy { retrofit.create(YoutubeApiService::class.java)}
}