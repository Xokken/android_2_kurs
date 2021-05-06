package com.example.android_2_kurs.di.modules

import android.content.Context
import com.example.android_2_kurs.data.RoomRepositoryImpl
import com.example.android_2_kurs.data.room.AppDatabase
import com.example.android_2_kurs.domain.RoomRepository
import dagger.Module
import dagger.Provides


@Module
class DatabaseModule {

    @Provides
    fun provideAppDatabase(context: Context): AppDatabase = AppDatabase(context)

    @Provides
    fun provideRoomRepository(appDatabase: AppDatabase): RoomRepository = RoomRepositoryImpl(appDatabase)
}