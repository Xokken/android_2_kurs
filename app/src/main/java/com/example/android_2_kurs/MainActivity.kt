package com.example.android_2_kurs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editNameButton.setOnClickListener {

            if (nameUser.visibility == View.VISIBLE) {
                nameUser.setVisibility(View.INVISIBLE)
                id_editNameText.setVisibility(View.VISIBLE)
                id_editNameText.setText(nameUser.text)
            } else {
                nameUser.setVisibility(View.VISIBLE)
                id_editNameText.setVisibility(View.INVISIBLE)
                nameUser.text = id_editNameText.text
            }
        }
    }
}