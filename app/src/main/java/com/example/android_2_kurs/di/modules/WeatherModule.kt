package com.example.android_2_kurs.di.modules

import com.example.android_2_kurs.data.LocationRepositoryImpl
import com.example.android_2_kurs.data.WeatherRepositoryImpl
import com.example.android_2_kurs.data.api.WeatherAPI
import com.example.android_2_kurs.domain.FindAndSaveCitiesUseCase
import com.example.android_2_kurs.domain.LocationRepository
import com.example.android_2_kurs.domain.WeatherRepository
import com.example.android_2_kurs.presentation.main.MainPresenter
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.Module
import dagger.Provides

@Module
class WeatherModule {

    @Provides
    fun provideWeatherRepository(
        weatherAPI: WeatherAPI
    ): WeatherRepository =
        WeatherRepositoryImpl(weatherAPI)


}