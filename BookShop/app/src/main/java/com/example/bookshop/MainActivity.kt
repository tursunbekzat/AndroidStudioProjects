package com.example.bookshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.bookshop.Constants.Constants
import com.example.bookshop.databinding.ActivityMainBinding
import com.example.bookshop.db.MyDbManager

class MainActivity : AppCompatActivity() {

//    database
    private val myDbManager = MyDbManager(this)

//    launchers for routing
    private var lancherSignIn:ActivityResultLauncher<Intent>? = null
    private var lancherSignUp:ActivityResultLauncher<Intent>? = null
    private var lancherWelcome:ActivityResultLauncher<Intent>? = null

//    bindingClass
    private lateinit var bindingClass: ActivityMainBinding

//    onCreate function
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.title = ""

//        open db
        myDbManager.openDb()
//        myDbManager.insertToDbBook("Граф Монте-Кристо", "Александр Дюма", "«Граф Мо́нте-Кри́сто» — приключенческий роман Александра Дюма, классика французской литературы, написанный в 1844—1846 годах.", 4500.0)
//        Log.d("MyLog", myDbManager.tableExists("books").toString())
//        myDbManager.execSQL()

//        waiting result from activities(launchers)
        lancherSignUp = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
            if(result.resultCode == RESULT_OK){
                Log.d("MyLog", "ResultSignUp")
                val name = result.data?.getStringExtra(Constants.NAME).toString()
                val surname = result.data?.getStringExtra(Constants.SURNAME).toString()
                val login = result.data?.getStringExtra(Constants.LOGIN).toString()
                val password = result.data?.getStringExtra(Constants.PASSWORD).toString()
                myDbManager.insertToDb(login, password, name, surname)
            }
        }

        lancherSignIn = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result: ActivityResult ->
                if(result.resultCode == RESULT_OK){
                    Log.d("MyLog", "ResultSignIn")
                    val i = Intent(this, Welcome::class.java)
                    i.putExtra("login", result.data?.getStringExtra("login").toString())
                    lancherWelcome?.launch(i)
                }
        }

        lancherWelcome = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result: ActivityResult ->
                if(result.resultCode == RESULT_OK){
                    Log.d("MyLog", "Welcome page")
                }
        }

    }

    fun onClickSignup(view: View) {
        Log.d("MyLog", "button SignUp")
        val i = Intent(this, SignUp::class.java)
        lancherSignUp?.launch(i)
    }

    fun onClickSignin(view: View) {
        Log.d("MyLog", "button SignIn")
        val i = Intent(this, SignIn::class.java)
        lancherSignIn?.launch(i)
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

}