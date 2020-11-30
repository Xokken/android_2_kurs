package com.example.android_2_kurs.layout

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_2_kurs.R
import com.example.android_2_kurs.entity.SongAdapter
import com.example.android_2_kurs.entity.Song
import com.example.android_2_kurs.layout.fragment.SongListFragment

class MainActivity : AppCompatActivity() {
    private var adapter: SongAdapter? = null
    lateinit var mp: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mp = MediaPlayer.create(this, R.raw.a)
        setContentView(R.layout.activity_main)
        initFragment()
    }

    private fun initFragment(){
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, SongListFragment.newInstance("1", "2"))
            .commit()
    }

}