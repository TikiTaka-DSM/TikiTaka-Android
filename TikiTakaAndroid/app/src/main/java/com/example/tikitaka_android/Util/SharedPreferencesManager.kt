package com.example.tikitaka_android.Util

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager {

    private val sharedPref: SharedPreferences = TikiTakaApplication.context!!.getSharedPreferences(
        MY_APP_PREFERENCES,
        Context.MODE_PRIVATE
    )

    var accessToken: String?
        get() = sharedPref.getString(SAVE_TOKEN, null)
        set(value) {
            val editor = sharedPref.edit()
            editor.putString(SAVE_TOKEN, value)
            editor.apply()
        }

    var refreshToken: String?
        get() = sharedPref.getString(SAVE_REFRESH, null)
        set(value) {
            val editor = sharedPref.edit()
            editor.putString(SAVE_REFRESH, value)
            editor.apply()
        }

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