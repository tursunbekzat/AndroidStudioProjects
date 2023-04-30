package com.example.bookshop.Retrofit

import retrofit2.http.GET

interface ApiInterface {

    @GET("/api/books/")
    suspend fun getBooks(): List<BookModule>
}