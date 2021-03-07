package com.example.tikitaka_android.home.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tikitaka_android.home.data.FriendListData
import com.example.tikitaka_android.network.Result
import com.example.tikitaka_android.home.data.HomeRepository
import com.example.tikitaka_android.home.data.RoomListData
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = HomeRepository()

    private val _searchLiveData = MutableLiveData<Boolean>()
    private val _mySearchLiveData = MutableLiveData<Boolean>()
    private val _friendListLiveData = MutableLiveData<FriendListData>()
    private val _roomListLiveData = MutableLiveData<RoomListData>()

    val searchLiveData: LiveData<Boolean> get() = _searchLiveData
    val mySearchLiveData: LiveData<Boolean> get() = _mySearchLiveData
    val friendListLiveData: LiveData<FriendListData> get() = _friendListLiveData
    val roomListLiveData: LiveData<RoomListData> get() = _roomListLiveData

    fun searchFriend(id: String) {
        viewModelScope.launch {
            val result = repository.searchFriend(id)
            if(result is Result.Success) setSearchLiveData(result)
            else _searchLiveData.value = false
        }
    }

    fun myFriendSearch(id: String) {
        viewModelScope.launch {
            val result = repository.myFriendSearch(id)
            if(result is Result.Success) setMySearchLiveData(result)
            else _mySearchLiveData.value = false
        }
    }

    private fun setSearchLiveData(result: Result.Success<Unit>) {
        if(result.code == 200) _searchLiveData.value = true
        else if(result.code == 404) _searchLiveData.value = false
    }

    private fun setMySearchLiveData(result: Result.Success<Unit>) {
        if(result.code == 200) _mySearchLiveData.value = true
        else if(result.code == 404) _mySearchLiveData.value = false
    }

    fun getFriendsList(){
        viewModelScope.launch {
            val result = repository.getFriendsList()
            if(result is Result.Success){
                setFriendListLiveData(result)
            } else {
                Log.e("getFriendsList", "fail")
            }
        }
    }

    private fun setFriendListLiveData(result: Result.Success<FriendListData>) {
        if(result.code == 200) {
            Log.e("homeViewModel",result.toString())
            _friendListLiveData.value = result.data
        }
    }

    fun getRoomList(){
        viewModelScope.launch {
            val result = repository.getRoomList()
            if(result is Result.Success){
                setRoomListLiveData(result)
            } else {
                Log.e("getRoomList","fail")
            }
        }
    }

    private fun setRoomListLiveData(result: Result.Success<RoomListData>) {
        if(result.code == 200) {
            _roomListLiveData.value = result.data
        }
    }
}