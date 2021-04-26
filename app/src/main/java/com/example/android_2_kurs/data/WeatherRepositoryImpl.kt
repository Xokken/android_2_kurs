package com.example.android_2_kurs.data

import com.example.android_2_kurs.data.api.WeatherAPI
import com.example.android_2_kurs.data.api.response.WeatherResponse
import com.example.android_2_kurs.data.api.response.WeatherResponseList
import com.example.android_2_kurs.domain.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherAPI: WeatherAPI
    ) : WeatherRepository {
    private val CONST_RAD = 20

    override suspend fun getWeatherCity(cityName: String): WeatherResponse = weatherAPI.getWeatherByName(cityName)

    override suspend fun getWeatherById(id: Int): WeatherResponse = weatherAPI.getWeatherById(id)

    override suspend fun getWeatherCities(
        latitude: Double,
        longitude: Double
    ): WeatherResponseList = weatherAPI.getWeatherCities(latitude, longitude, CONST_RAD)
}