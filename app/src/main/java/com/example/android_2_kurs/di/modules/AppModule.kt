package com.example.android_2_kurs.di.modules

import android.app.Application
import android.content.Context
import com.example.android_2_kurs.ApplicationDelegate
import com.example.android_2_kurs.data.LocationRepositoryImpl
import com.example.android_2_kurs.data.RoomRepositoryImpl
import com.example.android_2_kurs.data.WeatherRepositoryImpl
import com.example.android_2_kurs.data.api.WeatherAPI
import com.example.android_2_kurs.data.room.AppDatabase
import com.example.android_2_kurs.domain.LocationRepository
import com.example.android_2_kurs.domain.RoomRepository
import com.example.android_2_kurs.domain.WeatherRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Module
class AppModule{

    @Provides
    fun bindContext(application: ApplicationDelegate): Context = application.applicationContext


    @Provides
    fun provideFusedClient(context: Context) : FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @Provides
    fun provideCoroutineContext(): CoroutineContext = Dispatchers.IO

    @Provides
    fun provideLocationRepository(client: FusedLocationProviderClient): LocationRepository =
        LocationRepositoryImpl(client)

}