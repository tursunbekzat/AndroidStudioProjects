package com.example.bookshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
//import android.view.Window
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.bookshop.databinding.ActivityMenuBinding
import com.example.bookshop.db.MyDbManager

class Welcome : AppCompatActivity() {

    private var launcherBookInfo:ActivityResultLauncher<Intent>? = null
    private val db = MyDbManager(this)
    private lateinit var bindingClass: ActivityMenuBinding
    private var lancherList: ActivityResultLauncher<Intent>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMenuBinding.inflate(layoutInflater)
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(bindingClass.root)

        lancherList = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result: ActivityResult ->
            if(result.resultCode == RESULT_OK){
                Log.d("MyLog", "ListView")
            }
            else{
                Log.d("MyLog", "error")
            }
        }

        launcherBookInfo = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result: ActivityResult ->
            if(result.resultCode == RESULT_OK){
                Log.d("MyLog", "Bookinfo successfully closed")
            }
        }

        bindingClass.apply {

            buttonFind.setOnClickListener{
                db.openDb()

                val book:Book? = db.searchBook(edittextFind.text.toString())
                if (book != null){
                    textresult.visibility = View.INVISIBLE
                    textdescription.visibility = View.VISIBLE
                    textdescription.text = book.description
                    imagebookmenu.visibility = View.VISIBLE
                    buttongoto.visibility = View.VISIBLE
                    buttongoto.setOnClickListener {
                        val i = Intent(this@Welcome, BookInfo::class.java)
                        i.putExtra("book", book)
                        launcherBookInfo?.launch(i)
                    }

                }else{
                    imagebookmenu.visibility = View.INVISIBLE
                    textresult.visibility = View.VISIBLE
                    buttongoto.visibility = View.INVISIBLE
                    textdescription.visibility = View.INVISIBLE
                }
            }
        }

    }

    fun onClickList(view: View) {
        Log.d("MyLog", "button ListView")
        val i = Intent(this, List::class.java)
        i.putExtra("login", intent.getStringExtra("login").toString())
        lancherList?.launch(i)
    }

    override fun onDestroy() {
        db.closeDb()
        super.onDestroy()
    }
}