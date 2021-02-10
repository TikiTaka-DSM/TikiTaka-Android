package com.example.tikitaka_android.chat.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tikitaka_android.chat.data.ChatListData
import com.example.tikitaka_android.chat.data.ChatRepository
import com.example.tikitaka_android.network.Result
import kotlinx.coroutines.launch

class ChatViewModel: ViewModel() {
    private var repository= ChatRepository()
    var chatListLiveData: MutableLiveData<ChatListData> = MutableLiveData()

    fun getChatList(roomID: Int){
        viewModelScope.launch {
            val result = repository.getChatList(roomID)

            if(result is Result.Success){
                setChatListLiveData(result)
            } else {
                Log.e("ChatViewModel", "getChatList fail")
            }
        }
    }

    private fun setChatListLiveData(result: Result.Success<ChatListData>) {
        if(result.code == 200){
            chatListLiveData.value = result.data
        }
    }

}