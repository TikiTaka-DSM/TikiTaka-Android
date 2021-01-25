package com.example.tikitaka_android.util

import android.app.Application

class TikiTakaApplication : Application() {

    companion object {
        var prefs: SharedPreferencesManager? = null
    }

    override fun onCreate() {
        prefs = SharedPreferencesManager(applicationContext)
        super.onCreate()
    }
}