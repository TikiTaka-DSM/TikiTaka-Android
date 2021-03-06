package com.example.tikitaka_android.network

import com.example.tikitaka_android.chat.data.ChatListData
import com.example.tikitaka_android.chat.data.ChatRoomData
import com.example.tikitaka_android.home.data.FriendListData
import com.example.tikitaka_android.home.data.RoomListData
import com.example.tikitaka_android.profile.data.MyProfileData
import com.example.tikitaka_android.profile.data.FriendProfileData
import com.example.tikitaka_android.profile.data.RoomData
import com.example.tikitaka_android.sign.data.SignUpRequest
import com.example.tikitaka_android.sign.data.TokenResponse
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface TikiTakaAPI {
    @POST("/user/auth")
    suspend fun login(@Body body: HashMap<String, String>): Response<TokenResponse>

    @POST("user")
    suspend fun signUp(@Body body: SignUpRequest): Response<Unit>

    @GET("/profile")
    suspend fun getMyProfile(
            @Header("Authorization") header: String,
    ): Response<MyProfileData>

    @GET("profile/{id}")
    suspend fun getFriendProfile(
            @Header("Authorization") header: String,
            @Path("id") id: String
    ): Response<FriendProfileData>

    @Multipart
    @PUT("/profile")
    suspend fun modifyProfile(
            @Header("Authorization") header: String,
            @Part img: MultipartBody.Part,
            @Part name: MultipartBody.Part,
            @Part stateMessage: MultipartBody.Part
    ): Response<Unit>

    @PUT("/friend/block/{id}")
    suspend fun block(
            @Header("Authorization") header: String,
            @Path("id") id: String
    ): Response<Unit>

    @POST("/friend/{id}")
    suspend fun addFriend(
            @Header("Authorization") header: String,
            @Path("id") id: String
    ): Response<Unit>

    @GET("/friend?")
    suspend fun searchFriend(
            @Query("id") id: String
    ): Response<Unit>

    @GET("/friends?")
    suspend fun myFriendSearch(
            @Header("Authorization") header: String,
            @Query("name") name: String
    ): Response<Unit>

    @GET("/friends")
    suspend fun getFriendsList(
            @Header("Authorization") header: String
    ): Response<FriendListData>

    @POST("/room")
    suspend fun joinRoom(
            @Header("Authorization") header: String,
            @Body friend: JsonObject
    ): Response<ChatRoomData>

    @GET("/rooms")
    suspend fun getRoomList(
            @Header("Authorization") header: String
    ): Response<RoomListData>

    @GET("/room/{id}")
    suspend fun getChatList(
            @Header("Authorization") header: String,
            @Path("id") id: Int
    ): Response<ChatListData>
}