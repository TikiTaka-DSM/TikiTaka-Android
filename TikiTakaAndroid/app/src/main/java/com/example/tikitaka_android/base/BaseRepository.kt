package com.example.tikitaka_android.base

import com.example.tikitaka_android.network.Result
import com.example.tikitaka_android.network.safeApiCall
import com.example.tikitaka_android.util.TikiTakaApplication
import retrofit2.Response

open class BaseRepository {

    suspend fun <T : Any> mappingToResult(result: suspend () -> Response<T>): Result<T> {
        return safeApiCall(call = result)
    }

    fun getAccessToken(): String? {
        return TikiTakaApplication.prefs!!.getToken()
    }
}