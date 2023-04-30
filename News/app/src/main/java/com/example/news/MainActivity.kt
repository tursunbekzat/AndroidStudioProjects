package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.news.databinding.ActivityMainBinding
import com.example.news.databinding.FragmentSplashBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val mBinding get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.fragment_splash)
        Handler(Looper.myLooper()!!).postDelayed({
            setContentView(mBinding.root)
            bottom_nav_menu.setupWithNavController(
                navController = nav_host_fragment.findNavController()
            )
        }, 5000)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}