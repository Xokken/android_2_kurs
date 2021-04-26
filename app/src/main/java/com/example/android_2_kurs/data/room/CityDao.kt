package com.example.android_2_kurs.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_2_kurs.presentation.entity.City

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: City)

    @Query("DELETE FROM city")
    fun deleteAllCities()

    @Query("SELECT * FROM city")
    fun getAllCities(): List<City>
}