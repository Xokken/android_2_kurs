package com.example.android_2_kurs.presentation.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class City(
    @PrimaryKey var id: Int,
    var name: String,
    var temperature: Double
)