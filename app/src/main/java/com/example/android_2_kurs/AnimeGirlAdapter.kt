package com.example.android_2_kurs

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AnimeGirlAdapter (private var list: List<AnimeGirl>, private val itemClick: (AnimeGirl) -> Unit) : RecyclerView.Adapter<AnimeGirlHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeGirlHolder = AnimeGirlHolder.create(parent, itemClick)

    override fun onBindViewHolder(holder: AnimeGirlHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size

}