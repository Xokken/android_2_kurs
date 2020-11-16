package com.example.android_2_kurs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.action_home -> {
                    onClickHome()
                    true
                }
                R.id.action_places -> {
                    onClickPlaces()
                    true
                }
                R.id.action_photos -> {
                    onClickPhoto()
                    true
                }
                else -> false
            }

        }

    }

    private fun onClickHome(){
        supportFragmentManager.beginTransaction().replace(R.id.container_main,HomeFragment()).commit()
    }

    private fun onClickPhoto(){
        supportFragmentManager.beginTransaction().replace(R.id.container_main,PhotosFragment()).commit()
    }

    private fun onClickPlaces(){
        supportFragmentManager.beginTransaction().replace(R.id.container_main,PlacesFragment()).commit()
    }
}