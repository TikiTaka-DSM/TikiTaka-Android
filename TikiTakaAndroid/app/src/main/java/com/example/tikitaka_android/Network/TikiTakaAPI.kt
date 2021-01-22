package com.example.tikitaka_android.Network

import com.example.tikitaka_android.Profile.Data.MyProfileResponse
import com.example.tikitaka_android.Profile.Data.ProfileResponse
import com.example.tikitaka_android.Sign.Data.SignUpRequest
import com.example.tikitaka_android.Sign.Data.TokenResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface TikiTakaAPI{
    @POST("/user/auth")
    suspend fun login(@Body body: HashMap<String,String>): Response<TokenResponse>

    @POST("user")
    suspend fun signUp(@Body body:SignUpRequest):Response<Unit>

    @GET("/profile")
    suspend fun myProfile(
        @Header("Authorization") header: String,
    ): Response<MyProfileResponse>

    @GET("profile/{id}")
    suspend fun getProfile(
        @Path("id") id: String
    ): Response<ProfileResponse>

    @Multipart
    @PUT("/profile")
    suspend fun modifyProfile(
        @Header("Authorization") header: String,
        @PartMap part: HashMap<String,MultipartBody.Part>
    ): Response<Unit>

    @DELETE("/friend/block/{id}")
    suspend fun block(
        @Header("Authorization") header: String,
        @Path("id") id: String
    ): Response<Unit>

    @POST ("/friend/{id}")
    suspend fun addFriend(
        @Header("Authorization") header: String,
        @Path("id") id: String
    ): Response<Unit>

}