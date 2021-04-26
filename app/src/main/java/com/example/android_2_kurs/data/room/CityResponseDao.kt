package com.example.android_2_kurs.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_2_kurs.presentation.entity.CityResponse

@Dao
interface CityResponseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cityResponse: CityResponse)

    @Query("Select * from CityResponse where id = :id")
    fun getCityResponse(id: Int): CityResponse
}