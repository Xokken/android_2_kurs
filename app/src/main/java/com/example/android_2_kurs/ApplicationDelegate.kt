package com.example.android_2_kurs

import android.app.Application
import moxy.MvpFacade

class ApplicationDelegate: Application() {
    override fun onCreate() {
        super.onCreate()
        MvpFacade.init()
    }
}