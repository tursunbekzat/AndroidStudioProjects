package com.example.bookshopapp.Retrofit

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookshopapp.R

class MainActivity2 : AppCompatActivity( ) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
    }

    private fun getData() {

//        RetrofitInstance.apinterface.getData()
    }
}