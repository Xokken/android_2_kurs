package com.example.android_2_kurs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profile_girl.*

class ProfileGirl : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_girl)
        val girlsList = GirlsRepository.getRepository()
        val id = intent.getStringExtra("id")?.toInt()
        image.setImageResource(girlsList[id!!].img)
        name.text = girlsList[id].name
        desc.text = girlsList[id].desc
    }
}