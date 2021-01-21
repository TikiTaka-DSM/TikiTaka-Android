package com.example.tikitaka_android.Util

import android.app.Application
import android.content.Context

class TikiTakaApplication : Application() {

    companion object {
        var prefs: SharedPreferencesManager? = null
    }

    override fun onCreate() {
        val prefs = SharedPreferencesManager(applicationContext)
        super.onCreate()
    }
}