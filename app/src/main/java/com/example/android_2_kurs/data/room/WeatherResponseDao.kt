package com.example.android_2_kurs.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.android_2_kurs.presentation.entity.CityResponse

@Dao
interface WeatherResponseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cityResponse: CityResponse)


}