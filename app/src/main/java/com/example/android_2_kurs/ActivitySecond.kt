package com.example.android_2_kurs

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class ActivitySecond : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        buttonReturn.setOnClickListener{
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra("KEY","Saber One Love")
            })
            finish()
        }
    }
}