package com.example.tikitaka_android.profile.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tikitaka_android.network.Result
import com.example.tikitaka_android.profile.data.ProfileRepository
import com.example.tikitaka_android.profile.data.FriendProfileData
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val repository =  ProfileRepository()
    val friendProfileLiveData: MutableLiveData<FriendProfileData> = MutableLiveData()
    val blockLiveData: MutableLiveData<Int> = MutableLiveData()
    val addFriendLiveData: MutableLiveData<Int> = MutableLiveData()

    fun getFriendProfile(id: String) {
        viewModelScope.launch {
            when(val result = repository.getFriendProfile(id)){
                is Result.Success -> getFriendProfileSuccess(result)

                is Result.Error -> Log.e("ProfileViewModel", (result as Result.Error).exception)
            }
        }
    }

    fun setBlock(id: String){
        viewModelScope.launch {
            when(val result = repository.setBlock(id)){
                is Result.Success -> setBlockSuccess(result)

                is Result.Error -> Log.e("ProfileViewModel", (result as Result.Error).exception)
            }
        }
    }

    fun addFriend(id: String) {
        viewModelScope.launch {
            when(val result = repository.addFriend(id)){
                is Result.Success -> setAddFriend(result)

                is Result.Error -> Log.e("ProfileViewModel", (result as Result.Error).exception)
            }
        }
    }

    private fun getFriendProfileSuccess(result: Result.Success<FriendProfileData>) {
        if (result.code == 200) {
            val data = result.data
            Log.e("ProfileViewModel",data.toString())

            if (data != null) {
                friendProfileLiveData.postValue(data)
            }
        }
    }

    private fun setAddFriend(result: Result.Success<Unit>) {
        addFriendLiveData.value = result.code
    }

    private fun setBlockSuccess(result: Result.Success<Unit>) {
        blockLiveData.value = result.code
    }

}