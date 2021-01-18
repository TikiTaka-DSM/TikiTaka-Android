package com.example.tikitaka_android.Base

import com.example.tikitaka_android.Network.Result
import com.example.tikitaka_android.Network.safeApiCall
import com.example.tikitaka_android.Util.SharedPreferencesManager
import retrofit2.Response

open class BaseRepository {
    val sharedPreferences = SharedPreferencesManager.getInstance()

    suspend fun <T : Any> mappingToResult(result: suspend () -> Response<T>): Result<T> {
        return safeApiCall(call = result)
    }
}