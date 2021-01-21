package com.example.tikitaka_android.Util

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {

    private val pref: SharedPreferences = context.getSharedPreferences("TikiTaka-Pref",Context.MODE_PRIVATE)

    fun getToken(): String {
        return pref.getString(getKey(true),null).toString()
    }

    fun saveToken(access: Boolean, token: String) {
        pref.edit().let{
            it.putString(getKey(access),token)
            it.apply()
        }
    }

    fun removeToken(){
        pref.edit().let {
            it.remove(getKey(true))
            it.apply()
        }
    }

    private fun getKey(access: Boolean): String {
        return if(access) "accessToken" else "refreshToken"
    }



}
