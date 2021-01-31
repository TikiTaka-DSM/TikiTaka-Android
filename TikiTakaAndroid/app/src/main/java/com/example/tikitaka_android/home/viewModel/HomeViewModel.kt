package com.example.tikitaka_android.home.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tikitaka_android.home.data.FriendListData
import com.example.tikitaka_android.network.Result
import com.example.tikitaka_android.home.data.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = HomeRepository()
    var searchLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var friendListLiveData: MutableLiveData<FriendListData> = MutableLiveData()

    fun searchFriend(id: String) {
        viewModelScope.launch {
            val result = repository.searchFriend(id)
            if(result is Result.Success) setSearchLiveData(result)
            else searchLiveData.postValue(false)
        }
    }

    private fun setSearchLiveData(result: Result.Success<Unit>) {
        if(result.code == 200)searchLiveData.postValue(true)
        else if(result.code == 404) searchLiveData.postValue(false)
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
            friendListLiveData.postValue(result.data)
        }
    }
}