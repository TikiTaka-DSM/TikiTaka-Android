package com.example.tikitaka_android.profile.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tikitaka_android.network.Result
import com.example.tikitaka_android.profile.data.ProfileRepository
import com.example.tikitaka_android.profile.data.FriendProfileData
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val repository =  ProfileRepository()

    private val _friendProfileLiveData = MutableLiveData<FriendProfileData>()
    private val _blockLiveData = MutableLiveData<Int>()
    private val _addFriendLiveData = MutableLiveData<Int>()

    val friendProfileLiveData: LiveData<FriendProfileData> get() = _friendProfileLiveData
    val blockLiveData: LiveData<Int> get() = _blockLiveData
    val addFriendLiveData: LiveData<Int> get() = _addFriendLiveData

    fun getFriendProfile(id: String) {
        viewModelScope.launch {
            when(val result = repository.getFriendProfile(id)){
                is Result.Success -> getFriendProfileSuccess(result)

                is Result.Error -> Log.e("ProfileViewModel", result.exception)
            }
        }
    }

    fun setBlock(id: String){
        viewModelScope.launch {
            when(val result = repository.setBlock(id)){
                is Result.Success -> setBlockSuccess(result)

                is Result.Error -> Log.e("ProfileViewModel", result.exception)
            }
        }
    }

    fun addFriend(id: String) {
        viewModelScope.launch {
            when(val result = repository.addFriend(id)){
                is Result.Success -> setAddFriend(result)

                is Result.Error -> Log.e("ProfileViewModel", result.exception)
            }
        }
    }

    fun joinRoom(friendID: String) {
        viewModelScope.launch {

        }
    }

    private fun getFriendProfileSuccess(result: Result.Success<FriendProfileData>) {
        if (result.code == 200) {
            val data = result.data
            Log.e("ProfileViewModel",data.toString())

            if (data != null) {
                _friendProfileLiveData.postValue(data)
            }
        }
    }

    private fun setAddFriend(result: Result.Success<Unit>) {
        _addFriendLiveData.value = result.code
    }

    private fun setBlockSuccess(result: Result.Success<Unit>) {
        _blockLiveData.value = result.code
    }


}