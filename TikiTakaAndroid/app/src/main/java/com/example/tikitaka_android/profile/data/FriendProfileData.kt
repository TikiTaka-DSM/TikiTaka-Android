package com.example.tikitaka_android.profile.data

import com.google.gson.annotations.SerializedName

data class FriendProfileData(
        @SerializedName("profileData") val profileData: ProfileData,
        @SerializedName("state") val state: State,
        @SerializedName("roomData") val roomData: RoomData
)

data class ProfileData(
    @SerializedName("id") val id: String,
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("statusMessage") val statusMessage: String
    )

data class State(
    @SerializedName("friend") val friend: Boolean,
    @SerializedName("block") val block: Boolean
)

data class RoomData(
    @SerializedName("roomId") val roomId: Int
)

