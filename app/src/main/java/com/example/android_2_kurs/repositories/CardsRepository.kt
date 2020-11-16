package com.example.android_2_kurs.repositories

import com.example.android_2_kurs.PhotosCard
import com.example.android_2_kurs.R

object CardsRepository {
    val imagesList_1 : List<Int> = listOf(R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d)

    val imagesList_2 : List<Int> = listOf(R.drawable.d, R.drawable.c, R.drawable.a)

    val cardsList : List<PhotosCard> = listOf(
        PhotosCard("Post 1","Asdfghhgfdsa123 Zxcvbnmmnbvcxz22322", imagesList_1),
        PhotosCard("Post 2","Asdfghhgfdsa123 Zxcvbnmmnbvcxz22322 Asdfghhgfdsa123 Zxcvbnmmnbvcxz22322", imagesList_1),
        PhotosCard("Post 3","werw er  we re were we rew er ", imagesList_2),
        PhotosCard("Post 4","Asdfghhgfdsa123 Zxcvbnmmnbvcxz22322 ORAORAORAORAORAAAAA", imagesList_2)
    )
}