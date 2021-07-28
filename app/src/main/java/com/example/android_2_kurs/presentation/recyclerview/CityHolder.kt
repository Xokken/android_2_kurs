package com.example.android_2_kurs.presentation.recyclerview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.android_2_kurs.R
import com.example.android_2_kurs.presentation.entity.City
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.city_info.*

class CityHolder(
    override val containerView: View,
    private val itemClick: (City) -> Unit,
    private val context : Context
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private var city: City? = null

    fun bind(city: City) {
        cl_body.setOnClickListener {
            itemClick.invoke(city)
        }
        Log.d("test", city.toString())
        this.city = city
        with(city) {
            tv_nameCity.text = name
            when (temperature.toInt()){
                in -100..-20 ->  tv_temp.setTextColor(ContextCompat.getColor(context, R.color.very_cold))
                in -19..-0 -> tv_temp.setTextColor(ContextCompat.getColor(context, R.color.cold))
                in -0..9 -> tv_temp.setTextColor(ContextCompat.getColor(context, R.color.little_warm))
                in 10..19 -> tv_temp.setTextColor(ContextCompat.getColor(context, R.color.warm))
                in 20..100 -> tv_temp.setTextColor(ContextCompat.getColor(context, R.color.very_warm))
            }
            tv_temp.text = "${temperature} °C"
        }
    }

    companion object {
        fun create(parent: ViewGroup, itemClick: (City) -> Unit, context: Context): CityHolder =
            CityHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.city_info, parent, false),
                itemClick,
                context
            )
    }
}


