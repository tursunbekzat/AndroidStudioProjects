package com.example.bookshopapp.Retrofit

import retrofit2.Call
import retrofit2.http.GET

interface Apinterface {

    @GET("/Elixirs")
    suspend fun getData() : Call<List<Elixirs>>
}
