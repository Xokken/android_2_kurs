package com.example.android_2_kurs.data

import com.example.android_2_kurs.data.api.response.WeatherResponse
import com.example.android_2_kurs.data.room.AppDatabase
import com.example.android_2_kurs.domain.RoomRepository
import com.example.android_2_kurs.presentation.entity.City
import com.example.android_2_kurs.presentation.entity.CityResponse

class RoomRepositoryImpl(
    private val appDatabase: AppDatabase
): RoomRepository {

    override suspend fun saveListCities(list: List<City>) {
        for (city in list){
            appDatabase.cityDao().insert(city)
        }
    }

    override suspend fun saveWeatherResponse(response: CityResponse) {
        appDatabase.cityResponseDao().insert(response)
    }

    override suspend fun deleteAllCities() {
        appDatabase.cityDao().deleteAllCities()
    }

    override suspend fun getListCities(): List<City> {
        return appDatabase.cityDao().getAllCities()
    }

    override suspend fun getCityResponse(id: Int): CityResponse {
        return appDatabase.cityResponseDao().getCityResponse(id)
    }
}