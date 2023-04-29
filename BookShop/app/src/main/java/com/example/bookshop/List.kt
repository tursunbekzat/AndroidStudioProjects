package com.example.bookshop

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookshop.databinding.ActivityListBinding
import com.example.bookshop.db.MyDbManager

class List :AppCompatActivity() {

    private val myDbManager = MyDbManager(this)
    private var bookList = mutableListOf<MutableList<String>>()

    private lateinit var bindingClass: ActivityListBinding
    private val adapter = BookAdapter()

    private var launcherEdit: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityListBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        init()
        launcherEdit = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                Log.d("MyLog", "launcher Edit")
            }
            else{
                Log.d("MyLog", "Error Occurred")
            }
        }

    }
    private fun init(){
        myDbManager.openDb()
        bookList = myDbManager.readDbDataBook()
        if (bookList[0].size != 0) {
            for (i in 0 until bookList[0].size) {
                val book = Book(
                    bookList[0][i].toInt(),
                    bookList[1][i], bookList[2][i],
                    bookList[3][i], bookList[4][i].toDouble()
                )
                adapter.addBook(book)
            }
            adapter.notifyDataSetChanged()
        }

        bindingClass.apply {
            rvbooks.layoutManager = GridLayoutManager(this@List, 1)
            rvbooks.adapter = adapter
            buttonJust.setOnClickListener {
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (myDbManager.isAdmin(intent.getStringExtra("login").toString())) {
            menuInflater.inflate(R.menu.bottom_menu, menu)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Bookshop"
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val i = Intent(this@List, EditActivity::class.java)
        when(item.itemId){
            android.R.id.home -> finish()
            R.id.add -> {
                Toast.makeText(this, "Adding New Book", Toast.LENGTH_SHORT).show()
//                val i = Intent(this@List, EditActivity::class.java)
                i.putExtra("type", "add")
                launcherEdit?.launch(i)
            }
            R.id.update -> {
                Toast.makeText(this, "update", Toast.LENGTH_SHORT).show()
//                val i = Intent(this@List, EditActivity::class.java)
                i.putExtra("type", "update")
                launcherEdit?.launch(i)
            }
            R.id.search -> {
                Toast.makeText(this, "search", Toast.LENGTH_SHORT).show()
//                val i = Intent(this@List, EditActivity::class.java)
                i.putExtra("type", "search")
                launcherEdit?.launch(i)
            }
            R.id.delete -> {
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show()
//                val i = Intent(this@List, EditActivity::class.java)
                i.putExtra("type", "delete")
                launcherEdit?.launch(i)
            }
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
}