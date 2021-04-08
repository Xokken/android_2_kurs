package com.example.android_2_kurs.services

import com.example.android_2_kurs.entity.WeatherResponse
import com.example.android_2_kurs.entity.WeatherResponseList
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET(value = "weather?")
    suspend fun getWeatherByName(@Query("q") cityName: String) : WeatherResponse

    @GET(value = "weather?")
    suspend fun getWeatherById(@Query("id") cityId: Int) : WeatherResponse

    @GET(value = "find?")
    suspend fun getWeatherCities(@Query("lat") lat: Double, @Query("lon") lon: Double, @Query("cnt") cnt: Int) : WeatherResponseList
}