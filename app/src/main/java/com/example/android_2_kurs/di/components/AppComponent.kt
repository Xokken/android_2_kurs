package com.example.android_2_kurs.di.components

import com.example.android_2_kurs.ApplicationDelegate
import com.example.android_2_kurs.di.modules.AppModule
import com.example.android_2_kurs.di.modules.DatabaseModule
import com.example.android_2_kurs.di.modules.NetModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class, NetModule::class, DatabaseModule::class])
interface AppComponent {


    fun weatherBuilder(): WeatherSubComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: ApplicationDelegate): Builder

        fun build(): AppComponent
    }


}