package com.example.android_2_kurs.entity

import com.example.android_2_kurs.R

object SongRepository {
    private val songsList: ArrayList<Song> = arrayListOf(
        Song(1, "Cool Anime Track 1", "Fullmetal ost", R.drawable.aimg, R.raw.a),
        Song(2, "Cool Anime Track 2", "Fullmetal ost", R.drawable.bimg, R.raw.b),
        Song(3, "Cool Anime Track 3", "Fullmetal ost", R.drawable.cimg, R.raw.c),
        Song(4, "Cool Anime Track 4", "Fullmetal ost", R.drawable.dimg, R.raw.d),
        Song(5, "Cool Anime Track 5", "Fullmetal ost", R.drawable.eimg, R.raw.e),
        Song(6, "Cool Anime Track 6", "Fullmetal ost", R.drawable.fimg, R.raw.f),
        Song(7, "Cool Anime Track 7", "Fullmetal ost", R.drawable.gimg, R.raw.g),
        Song(8, "Cool Anime Track 8", "Fullmetal ost", R.drawable.himg, R.raw.h),
    )

    fun getRepository() = songsList
}