package com.example.tikitaka_android.profile.data

import com.example.tikitaka_android.base.BaseRepository
import com.example.tikitaka_android.chat.data.RoomData
import com.example.tikitaka_android.network.Result
import com.example.tikitaka_android.network.TikiTakaConnect
import okhttp3.MultipartBody

class ProfileRepository :BaseRepository() {
    var testToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTEzNzk4MzAsIm5iZiI6MTYxMTM3OTgzMCwianRpIjoiMWM4NGVjMTQtMDAwZC00MGFhLWI4MDctODlhOGU4YTZjY2Q1IiwiZXhwIjoxNjIwMDE5ODMwLCJpZGVudGl0eSI6Im1oIiwiZnJlc2giOmZhbHNlLCJ0eXBlIjoiYWNjZXNzIn0.3KUVMIqr9TbAuNuJGM2vYvDm1tbNua55jAJxqC4jRMA"

    suspend fun getMyProfile() : Result<MyProfileData> {
        return mappingToResult { TikiTakaConnect.createAPI().getMyProfile(testToken) }
    }

    suspend fun getFriendProfile(id: String): Result<FriendProfileData> {
        return mappingToResult { TikiTakaConnect.createAPI().getFriendProfile(testToken, id) }
    }

    suspend fun setBlock(id: String): Result<Unit> {
        return mappingToResult { TikiTakaConnect.createAPI().block(testToken,id) }
    }

    suspend fun addFriend(id: String): Result<Unit> {
        return mappingToResult { TikiTakaConnect.createAPI().addFriend(testToken, id) }
    }

    suspend fun modifyProfile(imagePart: MultipartBody.Part, namePart: MultipartBody.Part, messagePart: MultipartBody.Part): Result<Unit> {
        return mappingToResult { TikiTakaConnect.createAPI().modifyProfile(testToken,imagePart, namePart, messagePart) }
    }

}