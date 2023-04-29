package com.example.plantshandbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.plantshandbook.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditBinding
    private var indexImage = 0
    private var imageId  = R.drawable.plank1
    private val imgIdList = listOf(
        R.drawable.plank1,
        R.drawable.plank2,
        R.drawable.plank3,
        R.drawable.plank4,
        R.drawable.plank5
    )
    private var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initButtons()
    }

    private fun initButtons() = with(binding){
        btNextImage.setOnClickListener{
            indexImage++
            if (indexImage > imgIdList.size - 1) indexImage = 0
            imageId = imgIdList[indexImage]
            NextImage.setImageResource(imageId)

        }
        bDone.setOnClickListener {
            val plant = Plant(imageId, edTitle.text.toString(), edDesc.text.toString())
            val editIntent = Intent().apply {
                putExtra("plant", plant)
            }
            setResult(RESULT_OK, editIntent)
            finish()
        }
    }
}