package com.example.tikitaka_android.home.data

import android.util.Log
import com.example.tikitaka_android.base.BaseRepository
import com.example.tikitaka_android.network.Result
import com.example.tikitaka_android.network.TikiTakaConnect
import kotlin.coroutines.coroutineContext

class HomeRepository: BaseRepository() {
    val testToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTEzNzk4MzAsIm5iZiI6MTYxMTM3OTgzMCwianRpIjoiMWM4NGVjMTQtMDAwZC00MGFhLWI4MDctODlhOGU4YTZjY2Q1IiwiZXhwIjoxNjIwMDE5ODMwLCJpZGVudGl0eSI6Im1oIiwiZnJlc2giOmZhbHNlLCJ0eXBlIjoiYWNjZXNzIn0.3KUVMIqr9TbAuNuJGM2vYvDm1tbNua55jAJxqC4jRMA"

    suspend fun searchFriend(id: String): Result<Unit> {
        return mappingToResult { TikiTakaConnect.createAPI().searchFriend(id) }
    }

    suspend fun myFriendSearch(id: String): Result<Unit> {
        return mappingToResult { TikiTakaConnect.createAPI().myFriendSearch(testToken, id) }
    }

    suspend fun getFriendsList(): Result<FriendListData>{
        return mappingToResult { TikiTakaConnect.createAPI().getFriendsList(testToken) }
    }

    suspend fun getRoomList(): Result<RoomListData>{
        return mappingToResult { TikiTakaConnect.createAPI().getRoomList(testToken) }
    }
}