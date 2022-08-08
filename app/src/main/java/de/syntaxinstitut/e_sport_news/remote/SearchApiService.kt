package de.syntaxinstitut.e_sport_news.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.syntaxinstitut.e_sport_news.data.models.suche.GameResult
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


const val clientId = "deh7rpabvtjnkd8v2nl25bp2cktzvi"
    const val clientToken ="tjo0bak61n6myvqhy0zeo6bmfh7yji"

    const val BASE_URL2 = "https://api.igdb.com/v4/"

val client : OkHttpClient = OkHttpClient.Builder().addInterceptor(){ chain ->
    val request = chain.request().newBuilder()
        .addHeader("Authorization", "Bearer "+ clientToken)
        .addHeader("Client-ID", clientId)
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
    .baseUrl(BASE_URL2)
    .build()

interface SearchApiService{
    @POST("games")
    suspend fun getGames(@Body body : RequestBody):List<GameResult>

}
object SearchApi{
    val retrofitService: SearchApiService by lazy {
        retrofit.create(SearchApiService::class.java)}
}

