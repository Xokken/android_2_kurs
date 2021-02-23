package com.example.android_2_kurs.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_2_kurs.entity.City

class CityAdapter (private var list: List<City>, private val itemClick: (City) -> Unit) : RecyclerView.Adapter<CityHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder = CityHolder.create(parent, itemClick)

    override fun onBindViewHolder(holder: CityHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size

}