package com.example.android_2_kurs.domain

import com.example.android_2_kurs.data.api.response.WeatherResponse
import com.example.android_2_kurs.presentation.entity.City
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class FindCitiesUseCase(
    private val weatherRepository: WeatherRepository,
    private val context: CoroutineContext
) {

    suspend fun findWeatherCity(name: String): WeatherResponse{
        return withContext(context) {
            weatherRepository.getWeatherCity(name)
        }
    }

    suspend fun findWeatherCities(latitude: Double, longitude: Double): List<City>{
        val list = weatherRepository.getWeatherCities(latitude, longitude).list
        return list.map { weatherResponse ->
            City(
                weatherResponse.id,
                weatherResponse.name,
                weatherResponse.main.temp
            )
        }

    }
}