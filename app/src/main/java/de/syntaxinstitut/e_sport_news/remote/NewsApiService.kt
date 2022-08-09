package de.syntaxinstitut.e_sport_news.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.syntaxinstitut.e_sport_news.data.models.news.News
import de.syntaxinstitut.e_sport_news.data.models.news.NewsList
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

val BASE_URL_NEWS ="https://public.syntax-institut.de/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL_NEWS)
    .build()

interface NewsApiService{
    @GET("apps/KevinHering/data.json")
    suspend fun getNews(): NewsList
}
object NewsApi{
    val retrofitService:NewsApiService by lazy { retrofit.create(NewsApiService::class.java) }
}