package com.example.android_2_kurs

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(val list:List<PhotosCard>) : RecyclerView.Adapter<CardHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        return CardHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(list[position])
    }
}