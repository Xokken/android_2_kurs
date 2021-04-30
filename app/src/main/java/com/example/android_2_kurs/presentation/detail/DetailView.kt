package com.example.android_2_kurs.presentation.detail

import com.example.android_2_kurs.presentation.BaseView
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface DetailView: MvpView, BaseView {
    fun setName(name: String)
    fun setLat(lat: Double)
    fun setLon(lon: Double)
    fun setTemp(temp: Int)
    fun setSunrise(sunrise: Int)
    fun setSunset(sunset: Int)
    fun setHumidity(hum: Int)
    fun setDesc(desc: String)
    fun setMinTemp(minTemp: Double)
    fun setMaxTemp(maxTemp: Double)
    fun setFeelsTemp(feels: Int)
}