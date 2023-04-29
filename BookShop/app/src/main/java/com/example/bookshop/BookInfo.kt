package com.example.bookshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookshop.databinding.ActivityBookInfoBinding

class BookInfo : AppCompatActivity() {

    private lateinit var binding: ActivityBookInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            val book:Book = intent.getSerializableExtra("book") as Book
            var t = "Title: " + book.title
            textViewtitle.text = t
            t = "Author: " + book.author
            textViewauthor.text = t
            t = "Description: " + book.description
            textViewdescription.text = t
            t = "Cost: " + book.cost.toString()
            textViewcost.text = t
            button.setOnClickListener {
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

}