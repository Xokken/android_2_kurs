package com.example.android_2_kurs.entity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_2_kurs.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.song_profile.*

class SongViewHolder(override val containerView: View, private val itemClick: (Song) -> Unit)
    : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(song : Song){
        with(song){
            id_authorSong.text = author
            id_nameSong.text = name
            id_imageSong.setImageResource(song.cover)
        }
        itemView.setOnClickListener {
            itemClick(song)
        }
    }

    companion object{
        fun create(parent: ViewGroup, itemClick: (Song) -> Unit): SongViewHolder =
            SongViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.song_profile, parent,false), itemClick)
    }

}