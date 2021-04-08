package com.example.android_2_kurs.recyclerview

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android_2_kurs.entity.City

class CityAdapter(
    private val itemClick: (City) -> Unit,
    private val context: Context
) : ListAdapter<City, CityHolder>(object : DiffUtil.ItemCallback<City>() {
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean = oldItem == newItem
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder =
        CityHolder.create(parent, itemClick, context)

    override fun onBindViewHolder(holder: CityHolder, position: Int) =
        holder.bind(getItem(position))
}