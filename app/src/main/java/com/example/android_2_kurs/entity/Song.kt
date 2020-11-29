package com.example.android_2_kurs.entity

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class Song (
    val id: Int,
    val name: String,
    val author: String,
    @DrawableRes val cover: Int,
    @RawRes val file: Int,
)