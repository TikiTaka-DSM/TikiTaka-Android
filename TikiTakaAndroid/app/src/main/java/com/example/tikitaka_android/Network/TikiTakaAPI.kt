package com.example.tikitaka_android.Network

import com.example.tikitaka_android.Sign.Data.SignUpRequest
import com.example.tikitaka_android.Sign.Data.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TikiTakaAPI{
    @POST("/user/auth")
    suspend fun login(@Body body: HashMap<String,String>): Response<TokenResponse>

    @POST("user")
    suspend fun signUp(@Body body:SignUpRequest):Response<Unit>
}