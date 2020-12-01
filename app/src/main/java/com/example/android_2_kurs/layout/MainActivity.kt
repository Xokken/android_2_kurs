package com.example.android_2_kurs.layout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.android_2_kurs.R
import com.example.android_2_kurs.layout.fragment.SongFragment
import com.example.android_2_kurs.layout.fragment.SongListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
    }

    override fun onNewIntent(intent: Intent?) {
        var menuFragment = intent!!.extras
        var id = 1 + menuFragment!!.getString("key")!!.toInt()
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fade_in, R.anim.slide_out)
            .replace(R.id.fragment_container, SongFragment.newInstance(id))
            .commit()
        Log.println(Log.DEBUG, "sdf", menuFragment!!.getString("key"))
        super.onNewIntent(intent)

    }

    private fun initFragment(){
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fade_in, R.anim.slide_out)
            .add(R.id.fragment_container, SongListFragment.newInstance("1", "2"))
            .commit()
    }


}