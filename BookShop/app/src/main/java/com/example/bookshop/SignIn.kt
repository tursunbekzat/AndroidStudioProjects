package com.example.bookshop
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.bookshop.databinding.ActivitySignInBinding
import com.example.bookshop.db.MyDbManager

//import com.google.firebase.database.*

class SignIn : AppCompatActivity() {
    val myDbManager = MyDbManager(this)
    private lateinit var bindingClass: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        Log.d("MyLog", "onCreate SignIn")
    }
    fun onClickLogIn(view:View){
        Log.d("MyLod", "onClickSave in SigIn")
        myDbManager.openDb()
        val dataList = myDbManager.readDbData()
        bindingClass.lperror.visibility = View.INVISIBLE

//        val login = intent.getStringExtra("login").toString()
//        val password = intent.getStringExtra("password").toString()
        val curlogin = bindingClass.login.text.toString()
        val curpassword = bindingClass.password.text.toString()

        if(curlogin != "" && curpassword != ""){
            Log.d("MyLog", "Not Empty")
//            if (dataList.size == 0)
            if(dataList.size != 0 && curlogin in dataList[0] && curpassword in dataList[1] && dataList[0].indexOf(curlogin) == dataList[1].indexOf(curpassword)){
                Log.d("MyLog", "Exists user")
                intent.putExtra("login", curlogin)
                setResult(RESULT_OK, intent)
                finish()
            }else{
                Log.d("MyLog", "Not exists user")
                bindingClass.lperror.visibility = View.VISIBLE
                bindingClass.lperror.text = "Incorrect Login or Password!"
            }
        }else{
            Log.d("MyLog", "Error")
            bindingClass.lperror.visibility = View.VISIBLE
            bindingClass.lperror.text = "You write somewhere nothing!"
        }
    }
}