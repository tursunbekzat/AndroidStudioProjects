package com.example.bookshop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshop.databinding.ActivityBookBinding

class BookAdapter:RecyclerView.Adapter<BookAdapter.BookHolder>() {

    private var bookList = ArrayList<Book>()
    class BookHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = ActivityBookBinding.bind(item)
        fun bind(book: Book) = with(binding){
            imagebookbook.setImageResource(R.drawable.samalbooks)
            var t = texttitlebook.text.toString() + ": " + book.title
            texttitlebook.text = t
            t = textdescriptionbook.text.toString() + ": " + book.description
            textdescriptionbook.text = t
            t = textauthorbook.text.toString() + ": " + book.author
            textauthorbook.text = t
            t = textcostbook.text.toString() + ": " + book.cost.toString()
            textcostbook.text = t
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.activity_book, parent, false)
        return BookHolder(view)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        holder.bind(bookList[position])
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun addBook(book: Book){
        bookList.add(book)
        notifyDataSetChanged()
    }

    /*
    fun addAll(list: ArrayList<Book>){
        bookList.clear()
        bookList.addAll(list)
        notifyDataSetChanged()
    }
     */

}
