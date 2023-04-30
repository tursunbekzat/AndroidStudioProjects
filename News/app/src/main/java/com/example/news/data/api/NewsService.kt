package com.example.news.data.api

import com.example.news.utils.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("/v2/everything")
    suspend fun getEverything(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey:String = API_KEY
    )

    @GET("/v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String = "kz",
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey:String = API_KEY
    )

}