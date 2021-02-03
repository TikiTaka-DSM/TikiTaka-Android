package com.example.tikitaka_android.profile.data

import com.google.gson.annotations.SerializedName


data class MyProfileData(
        @SerializedName("profileData") val profileData: ProfileData
)