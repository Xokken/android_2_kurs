package com.example.android_2_kurs

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_2_kurs.entity.Song

class SongAdapter(private val songList: ArrayList<Song>,
                  private val itemClick: (Int) -> (Unit)) : RecyclerView.Adapter<SongViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder{
        return SongViewHolder.create(parent, itemClick)
    }

    override fun getItemCount(): Int = songList.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(songList[position])
    }

}