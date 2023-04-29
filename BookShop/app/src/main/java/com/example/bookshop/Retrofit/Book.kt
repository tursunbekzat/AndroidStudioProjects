package com.example.bookshop.Retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface Book {

    @GET("/api/books/")
    suspend fun getBooks(
//        @Query("q") query: String,
//        @Query("page") page:Int = 1,
//        @Query("apiKey") apiKey:String = ""
    ): List<BookModule>
}