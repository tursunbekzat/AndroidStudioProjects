package com.example.bookshop
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.bookshop.Constants.Constants
import com.example.bookshop.databinding.ActivitySignUpBinding
import com.example.bookshop.db.MyDbManager

class SignUp : AppCompatActivity() {
    private lateinit var bindingClass: ActivitySignUpBinding
    private val myDbManager = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        Log.d("MyLog", "onCreate in SignUp")
        bindingClass.textView.visibility = View.INVISIBLE
    }


    fun onClickSave(view: View){
        myDbManager.openDb()
        val dataList = myDbManager.readDbData()
        val lperrornull = R.string.lperrornull.toString()
        Log.d("MyLog", "onClickSave in SignUp")
        bindingClass.textView.text = lperrornull
        bindingClass.textView.visibility = View.INVISIBLE
        val curname = bindingClass.textname.text.toString()
        val cursurname = bindingClass.textsurname.text.toString()
        val curlogin = bindingClass.textlogin.text.toString()
        val curpassword = bindingClass.textpassword.text.toString()

        if (curname != "" && cursurname != "" && curlogin != "" && curpassword != "") {
            Log.d("MyLog", "Not empty lines")
            if(dataList.size != 0 && curlogin in dataList[0]){
                val exists = "This account already exists"
                bindingClass.textView.visibility = View.VISIBLE
                bindingClass.textView.text = exists
            }
            else {
                Log.d("MyLog", "Not")
                intent.putExtra(Constants.NAME, curname)
                intent.putExtra(Constants.SURNAME, cursurname)
                intent.putExtra(Constants.LOGIN, curlogin)
                intent.putExtra(Constants.PASSWORD, curpassword)
                setResult(RESULT_OK, intent)
                finish()
            }
        }else{
            Log.d("MyLog", "Error")
//            bindingClass.textView.text = "You write somewhere nothing!"
            bindingClass.textView.text = lperrornull
            bindingClass.textView.visibility = View.VISIBLE
        }
    }
}