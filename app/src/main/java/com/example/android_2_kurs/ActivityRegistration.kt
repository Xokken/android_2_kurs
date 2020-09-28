package com.example.android_2_kurs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_registration.*

class ActivityRegistration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        button1.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("name", et_name1.text.toString().trim())
            intent.putExtra("workplace", et_name2.text.toString().trim())
            intent.putExtra("city", et_name3.text.toString().trim())
            startActivity(intent)
            this.finish()
        }
    }
}