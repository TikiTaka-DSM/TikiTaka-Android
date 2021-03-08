package com.example.tikitaka_android.chat.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tikitaka_android.chat.data.ChatListData
import com.example.tikitaka_android.chat.data.ChatRepository
import com.example.tikitaka_android.chat.data.ChatRoomData
import com.example.tikitaka_android.network.Result
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class ChatAPIViewModel : ViewModel() {
    private var repository = ChatRepository()

    private val _chatListLiveData = MutableLiveData<ChatListData>()
    private val _joinRoomLiveData = MutableLiveData<Int>()

    val chatListLiveData: LiveData<ChatListData> get() = _chatListLiveData
    val joinRoomLiveData: LiveData<Int> get() = _joinRoomLiveData

    fun getChatList(roomID: Int) {
        viewModelScope.launch {
            val result = repository.getChatList(roomID)

            if (result is Result.Success) {
                setChatListLiveData(result)
            } else {
                Log.e("ChatViewModel", "getChatList fail")
            }
        }
    }

    private fun setChatListLiveData(result: Result.Success<ChatListData>) {
        if (result.code == 200) {
            _chatListLiveData.value = result.data
        }
    }

    fun joinRoom(friendId: String) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("friend", friendId)

        viewModelScope.launch {
            val result = repository.joinRoom(jsonObject)

            if (result is Result.Success) {
                setJoinRoomLiveData(result)
            } else if (result is Result.Error) {
                Log.e("ChatViewModel", result.exception)
            }
        }
    }

    private fun setJoinRoomLiveData(result: Result<ChatRoomData>) {

    }

}