package com.example.tikitaka_android.Network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TikiTakaConnect{
    private var retrofit: Retrofit
    private var api: TikiTakaAPI
    private val baseURL = "http://54.180.2.226/tikitaka/api"

    init{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        api = retrofit.create(TikiTakaAPI::class.java)
    }

    fun createAPI() = api

}