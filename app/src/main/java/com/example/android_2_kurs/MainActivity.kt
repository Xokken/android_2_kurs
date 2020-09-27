package com.example.android_2_kurs

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = intent
        nameUser.text = intent.getStringExtra("name")
        textView2.text = intent.getStringExtra("city")
        textView4.text = intent.getStringExtra("workplace")

        editNameButton.setOnClickListener {

            if (nameUser.visibility == View.VISIBLE) {
                nameUser.setVisibility(View.INVISIBLE)
                editNameText.setVisibility(View.VISIBLE)
                editNameText.setText(nameUser.text)
            } else {
                nameUser.setVisibility(View.VISIBLE)
                editNameText.setVisibility(View.INVISIBLE)
                nameUser.text = editNameText.text
            }
        }
    }
}