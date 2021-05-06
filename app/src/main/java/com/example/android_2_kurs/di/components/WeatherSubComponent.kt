package com.example.android_2_kurs.di.components

import com.example.android_2_kurs.di.modules.WeatherModule
import com.example.android_2_kurs.presentation.detail.InfoDetailActivity
import com.example.android_2_kurs.presentation.main.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [WeatherModule::class])
interface WeatherSubComponent {

    fun injectMain(main: MainActivity)
    fun injectDetail(detail: InfoDetailActivity)

    @Subcomponent.Builder
    interface Builder {

        fun setModule(weatherModule: WeatherModule): Builder
        fun build(): WeatherSubComponent

    }

}