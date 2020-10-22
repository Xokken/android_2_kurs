package com.example.android_2_kurs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var adapter: AnimeGirlAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = AnimeGirlAdapter(GirlsRepository.getRepository()) {
            Toast.makeText(this, "hi $it", Toast.LENGTH_SHORT).show()
        }
        animeGirls.adapter = adapter
        animeGirls.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        //rv_book.addItemDecoration(SpaceItemDecoration(this))

    }
}