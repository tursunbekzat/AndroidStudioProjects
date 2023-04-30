package com.example.bookshop.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://wolnelektury.pl/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val apinterface by lazy{
        retrofit.create(ApiInterface::class.java)
    }
}