package com.example.tikitaka_android.Util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPreferencesManager() {

    private var sharedPreference = TikiTakaApplication.context!!.getSharedPreferences(MY_APP_PREFERENCES, MODE_PRIVATE)

    var accessToken: String?
        get() = sharedPreference.getString(SAVE_TOKEN," ")
        set(value)= sharedPreference.edit().putString(SAVE_TOKEN,value).apply()

    var refreshToken: String?
        get() = sharedPreference.getString(SAVE_REFRESH, " ")
        set(value) = sharedPreference.edit().putString(SAVE_REFRESH,value).apply()


    companion object {
        private const val MY_APP_PREFERENCES = "Yally-Android"
        private const val SAVE_TOKEN = "accessToken"
        private const val SAVE_REFRESH = "refreshToken"
        private var instance: SharedPreferencesManager? = null

        @Synchronized
        fun getInstance(): SharedPreferencesManager {
            if (instance == null) instance = SharedPreferencesManager()
            return instance!!
        }

    }
}