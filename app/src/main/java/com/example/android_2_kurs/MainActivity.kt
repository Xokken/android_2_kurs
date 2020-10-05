package com.example.android_2_kurs

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonIntent.setOnClickListener(){
            val intent = Intent(Intent.ACTION_VIEW)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            if(intent.resolveActivity(packageManager)!=null){
                startActivityForResult(intent,1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            id_textView1.text = data?.getStringExtra("KEY")
            Toast.makeText(this,"You are pressed on my button, senpai!", Toast.LENGTH_SHORT).show()
        }
    }
}