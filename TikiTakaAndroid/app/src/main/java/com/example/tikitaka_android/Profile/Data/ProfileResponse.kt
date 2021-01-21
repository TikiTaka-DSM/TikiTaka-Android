package com.example.tikitaka_android.Profile.Data

data class ProfileResponse (
    val profileData: MyProfileResponse,
    val state: State
        )


data class State(
    val friend: Boolean,
    val block: Boolean
)