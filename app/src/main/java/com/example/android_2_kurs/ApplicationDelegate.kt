package com.example.android_2_kurs

import android.app.Application
import com.example.android_2_kurs.di.components.AppComponent
import com.example.android_2_kurs.di.components.DaggerAppComponent
import com.example.android_2_kurs.di.components.WeatherSubComponent
import com.example.android_2_kurs.di.modules.WeatherModule
import moxy.MvpFacade

class ApplicationDelegate: Application() {
    override fun onCreate() {
        super.onCreate()
        MvpFacade.init()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        weatherComponent = appComponent.weatherBuilder()
            .setModule(WeatherModule())
            .build()
    }

    companion object{
        lateinit var appComponent: AppComponent
        lateinit var weatherComponent: WeatherSubComponent
    }
}