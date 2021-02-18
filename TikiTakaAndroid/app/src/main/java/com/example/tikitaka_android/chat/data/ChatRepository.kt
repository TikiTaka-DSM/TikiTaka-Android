package com.example.tikitaka_android.chat.data

import com.example.tikitaka_android.base.BaseRepository
import com.example.tikitaka_android.home.data.RoomListData
import com.example.tikitaka_android.network.Result
import com.example.tikitaka_android.network.TikiTakaConnect
import com.example.tikitaka_android.profile.data.RoomData
import retrofit2.Response

class ChatRepository: BaseRepository() {
    var testToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTEzNzk4MzAsIm5iZiI6MTYxMTM3OTgzMCwianRpIjoiMWM4NGVjMTQtMDAwZC00MGFhLWI4MDctODlhOGU4YTZjY2Q1IiwiZXhwIjoxNjIwMDE5ODMwLCJpZGVudGl0eSI6Im1oIiwiZnJlc2giOmZhbHNlLCJ0eXBlIjoiYWNjZXNzIn0.3KUVMIqr9TbAuNuJGM2vYvDm1tbNua55jAJxqC4jRMA"

    suspend fun joinRoom(friendId: String): Result<RoomData> {
        return mappingToResult { TikiTakaConnect.createAPI().joinRoom(testToken, friendId) }
    }

    suspend fun getChatList(roomID: Int): Result<ChatListData> {
        return mappingToResult { TikiTakaConnect.createAPI().getChatList(testToken,roomID) }
    }
}