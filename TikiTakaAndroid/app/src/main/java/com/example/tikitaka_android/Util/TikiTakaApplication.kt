package com.example.tikitaka_android.Util

import android.app.Application
import android.content.Context

class TikiTakaApplication : Application() {

    companion object {
        lateinit var prefs: SharedPreferencesManager
    }

    override fun onCreate() {
        prefs = SharedPreferencesManager(applicationContext)
        super.onCreate()
    }
}