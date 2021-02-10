package com.example.tikitaka_android.chat.data

import com.google.gson.annotations.SerializedName

data class ChatListData (
        @SerializedName("roomData") val roomData: RoomData,
        @SerializedName("messageData") val messageData: List<MessageData>
)

data class MessageData (
    @SerializedName("user") val user: User,
    @SerializedName("message") val message: String? = null,
    @SerializedName("photo") val photo: String? = null,
    @SerializedName("voice") val voice: Any? = null,
    @SerializedName("createdAt") val createdAt: String
)

data class User (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("img") val img: String
)

data class RoomData (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)
