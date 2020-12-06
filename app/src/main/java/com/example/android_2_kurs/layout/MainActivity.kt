package com.example.android_2_kurs.layout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_2_kurs.R
import com.example.android_2_kurs.layout.fragment.SongFragment
import com.example.android_2_kurs.layout.fragment.SongListFragment

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
    }

    override fun onNewIntent(intent: Intent?) {
        val menuFragment = intent?.extras
        val id = menuFragment?.getInt("click")?.plus(2)
        id?.let { SongFragment.newInstance(it) }?.let {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.slide_out)
                .replace(R.id.fragment_container, it)
                .commit()
        }
        super.onNewIntent(intent)

    }

    private fun initFragment(){
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fade_in, R.anim.slide_out)
            .add(R.id.fragment_container, SongListFragment.newInstance("1", "2"))
            .commit()
    }




}