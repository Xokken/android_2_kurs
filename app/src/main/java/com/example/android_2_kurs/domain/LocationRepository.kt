package com.example.android_2_kurs.domain

import android.annotation.SuppressLint
import android.location.Location

interface LocationRepository {

    suspend fun getUserLocation(): Location
}