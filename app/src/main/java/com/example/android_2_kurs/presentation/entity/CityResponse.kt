package com.example.android_2_kurs.presentation.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityResponse(
    @PrimaryKey val id: Int,
    var name: String,
    var lat: Double,
    var lon: Double,
    var temp: Double,
    var sunrise: Int,
    var sunset: Int,
    var humidity: Int,
    var desc: String,
    var minTemp: Double,
    var maxTemp: Double,
    var feelsTemp: Double
)
