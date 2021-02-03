package com.example.tikitaka_android.home.data

import com.example.tikitaka_android.chat.data.User
import com.google.gson.annotations.SerializedName

data class FriendListData(
        @SerializedName("friends") val friends: List<User>
)

