package com.example.bookshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.bookshop.databinding.ActivityEditBinding
import com.example.bookshop.db.MyDbManager

class EditActivity : AppCompatActivity() {

    private val db = MyDbManager(this)

    private lateinit var binding: ActivityEditBinding
    private var launcherBookInfo:ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initbuttons()

        launcherBookInfo = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result: ActivityResult ->
                if(result.resultCode == RESULT_OK){
                    Log.d("MyLog", "Bookinfo successfully closed")
                }
        }
    }

    private fun initbuttons() = with(binding) {
        db.openDb()

        editTextbookoldtitle.visibility = View.INVISIBLE
        editTextbookoldauthor.visibility = View.INVISIBLE
        editTextTextbooktitle.visibility = View.VISIBLE
        editTextbookauthor.visibility = View.VISIBLE
        editTextbookdescriptionedit.visibility = View.VISIBLE
        editTextbookcostedit.visibility = View.VISIBLE

        when (intent.getStringExtra("type").toString()) {
            "add" -> {
                buttonCRUD.text = "add book"
                buttonCRUD.setOnClickListener {
                    db.insertToDbBook(editTextTextbooktitle.text.toString(), editTextbookauthor.text.toString(), editTextbookdescriptionedit.text.toString(), editTextbookcostedit.text.toString().toDouble())
                    val text = "Created New Book successfully"
                    Toast.makeText(this@EditActivity, text, Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK, intent)
                    finish()
                }
                buttonExit.setOnClickListener{
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
            "update" -> {
                editTextTextbooktitle.visibility = View.INVISIBLE
                editTextbookauthor.visibility = View.INVISIBLE
                editTextbookdescriptionedit.visibility = View.INVISIBLE
                editTextbookcostedit.visibility = View.INVISIBLE
                editTextbookoldtitle.visibility = View.VISIBLE
                editTextbookoldauthor.visibility = View.VISIBLE
                buttonCRUD.text = "update book"
                val oldtitle = editTextbookoldtitle.text.toString()
                val oldauthor = editTextbookoldauthor.text.toString()
                buttonCRUD.setOnClickListener {
                    editTextbookoldtitle.visibility = View.INVISIBLE
                    editTextbookoldauthor.visibility = View.INVISIBLE
                    editTextTextbooktitle.visibility = View.VISIBLE
                    editTextbookauthor.visibility = View.VISIBLE
                    editTextbookdescriptionedit.visibility = View.VISIBLE
                    editTextbookcostedit.visibility = View.VISIBLE
                    val book = db.searchBook(editTextbookoldtitle.text.toString())
                    if (book != null) {
                        Toast.makeText(this@EditActivity, "Book exists", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@EditActivity, "book not found", Toast.LENGTH_SHORT).show()
                    }
                    buttonCRUD.setOnClickListener {
                        var text = "Book updated successfully"
                        if (book != null) {
                            db.deleteBook(editTextbookoldtitle.text.toString(), editTextbookoldauthor.text.toString())
                            db.insertToDbBook(editTextTextbooktitle.text.toString(), editTextbookauthor.text.toString(), editTextbookdescriptionedit.text.toString(), editTextbookcostedit.text.toString().toDouble())
                        }
                        else text = "Book is not found!"
                        Toast.makeText(this@EditActivity, text, Toast.LENGTH_SHORT).show()
                        setResult(RESULT_OK, intent)
                        finish()
                    }
                }
                buttonExit.setOnClickListener{
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
            "search" -> {
                editTextbookauthor.visibility = View.INVISIBLE
                editTextbookdescriptionedit.visibility = View.INVISIBLE
                editTextbookcostedit.visibility = View.INVISIBLE
                buttonCRUD.text = "find book"
                buttonCRUD.setOnClickListener {
                    val book = db.searchBook(editTextTextbooktitle.text.toString())
                    if (book == null) {
                        val text = "Book is not found"
                        Toast.makeText(this@EditActivity, text, Toast.LENGTH_SHORT).show()
                        setResult(RESULT_OK, intent)
                        finish()
                    } else {
                        val i = Intent(this@EditActivity, BookInfo::class.java)
                        i.putExtra("book", book)
                        launcherBookInfo?.launch(i)
                    }
                }
                buttonExit.setOnClickListener{
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
            "delete" -> {
                buttonCRUD.text = "delete book"
                editTextbookdescriptionedit.visibility = View.INVISIBLE
                editTextbookcostedit.visibility = View.INVISIBLE
                buttonCRUD.setOnClickListener {
                    if (db.deleteBook(editTextTextbooktitle.text.toString(), editTextbookauthor.text.toString())) Toast.makeText(this@EditActivity, "the book was successfully deleted", Toast.LENGTH_SHORT).show()
                    else Toast.makeText(this@EditActivity, "book is not defined", Toast.LENGTH_SHORT).show()
                }
                buttonExit.setOnClickListener{
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
            else -> {}
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        db.closeDb()
    }
}