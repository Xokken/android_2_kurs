package com.example.android_2_kurs.domain

import com.example.android_2_kurs.data.api.response.WeatherResponse
import com.example.android_2_kurs.data.api.response.WeatherResponseList

interface WeatherRepository {
    suspend fun getWeatherCity(cityName: String) : WeatherResponse
    suspend fun getWeatherById(id: Int) : WeatherResponse
    suspend fun getWeatherCities(latitude: Double, longitude: Double) : WeatherResponseList

}