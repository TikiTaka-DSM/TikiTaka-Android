package com.example.tikitaka_android.Util

import android.app.Application
import android.content.Context

class TikiTakaApplication : Application() {
    companion object {
        var instance: TikiTakaApplication? = null
        private set

        val context: Context?
        get() = instance
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}