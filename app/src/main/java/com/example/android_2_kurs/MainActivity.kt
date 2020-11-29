package com.example.android_2_kurs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.android_2_kurs.entity.SongRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var adapter: SongAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = SongAdapter(SongRepository.getRepository()) {
            Toast.makeText(this, "hi $it", Toast.LENGTH_SHORT).show()
        }
        listSong.adapter = adapter
        listSong.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
    }
}