package com.example.android_2_kurs.domain

import com.example.android_2_kurs.presentation.entity.City
import com.example.android_2_kurs.presentation.entity.CityResponse

interface RoomRepository {
    suspend fun saveListCities(list: List<City>)
    suspend fun saveWeatherResponse(response: CityResponse)
    suspend fun getCityResponse(int: Int): CityResponse
    suspend fun deleteAllCities()
    suspend fun getListCities(): List<City>
}