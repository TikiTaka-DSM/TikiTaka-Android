package com.example.tikitaka_android.home.data

import com.example.tikitaka_android.chat.data.User
import com.google.gson.annotations.SerializedName

data class RoomListData(
        @SerializedName("rooms") val rooms: List<Room>
    )

data class Room(
    @SerializedName("roomId") val roomId: String,
    @SerializedName("user") val user: User,
    @SerializedName("lastMessage") val lastMessage: String
    )