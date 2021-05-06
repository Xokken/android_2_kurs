package com.example.android_2_kurs.domain

import com.example.android_2_kurs.data.api.response.WeatherResponse
import com.example.android_2_kurs.data.api.response.WeatherResponseList
import com.example.android_2_kurs.presentation.entity.City
import com.example.android_2_kurs.presentation.entity.CityResponse
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class FindAndSaveCitiesUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val roomRepository: RoomRepository,
    private val context: CoroutineContext
) {

    suspend fun getWeatherById(id: Int): WeatherResponse{
        return withContext(context) {
            weatherRepository.getWeatherById(id)
        }
    }

    suspend fun findWeatherCity(name: String): WeatherResponse{
        return withContext(context) {
            weatherRepository.getWeatherCity(name)
        }
    }

    suspend fun findWeatherCities(latitude: Double, longitude: Double): List<City> {
        return withContext(context) {
            val list = weatherRepository.getWeatherCities(latitude, longitude).list
            list.map { weatherResponse ->
                City(
                    weatherResponse.id,
                    weatherResponse.name,
                    weatherResponse.main.temp
                )
            }

        }
    }

    suspend fun clearAllCities(){
        withContext(context){
            roomRepository.deleteAllCities()
        }
    }

    suspend fun saveListCities(list: List<City>){
        withContext(context){
            roomRepository.saveListCities(list)
        }
    }

    suspend fun getListCities(): List<City>{
        return withContext(context){
            roomRepository.getListCities()
        }
    }

    suspend fun saveCityResponse(response: CityResponse){
        return withContext(context){
            roomRepository.saveWeatherResponse(response)
        }
    }

    suspend fun getCityResponse(id: Int): CityResponse{
        return withContext(context){
            roomRepository.getCityResponse(id)
        }
    }
}