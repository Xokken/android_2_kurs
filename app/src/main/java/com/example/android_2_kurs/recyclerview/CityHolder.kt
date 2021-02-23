package com.example.android_2_kurs.recyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_2_kurs.R
import com.example.android_2_kurs.activity.InfoDetailActivity
import com.example.android_2_kurs.entity.City
import kotlinx.android.extensions.LayoutContainer

class CityHolder(
    override val containerView: View,
    private val itemClick: (City) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private var city: City? = null

    init {
        itemView.setOnClickListener {
            val context = itemView.context
            val intent = Intent(context, InfoDetailActivity::class.java)
            intent.putExtra("id", id)
            context.startActivity(intent)
        }
    }

    fun bind(city: City) {
        this.city = city
        with(city) {
            .text = name
            descGirl.text = desc
        }
    }

    companion object {
        fun create(parent: ViewGroup, itemClick: (City) -> Unit): CityHolder =
            CityHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.city_info, parent, false),
                itemClick
            )
