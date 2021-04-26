package com.example.android_2_kurs.presentation.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class City(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "temperature") var temperature: Double
)