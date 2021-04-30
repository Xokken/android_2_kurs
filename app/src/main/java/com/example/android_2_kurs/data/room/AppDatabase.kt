package com.example.android_2_kurs.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android_2_kurs.data.api.response.WeatherResponse
import com.example.android_2_kurs.presentation.entity.City
import com.example.android_2_kurs.presentation.entity.CityResponse

@Database(entities = [City::class, CityResponse::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao
    abstract fun cityResponseDao() : CityResponseDao

    companion object {

        private const val DATABASE_NAME = "cityWeathers.db"

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        /*
        * Можно написть и так:
        * operator fun invoke(context: Context) = instance ?: buildDatabase(context).also { instance = it }
        *
        * Это более простой способо реализации без проверки на синхронизации потоков. Но может сложиться такая ситуация,
        * когда 2 разных потока придут сюда и создадут 2 разных объекта и у вас уже не синглтоновый объект
        */
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}