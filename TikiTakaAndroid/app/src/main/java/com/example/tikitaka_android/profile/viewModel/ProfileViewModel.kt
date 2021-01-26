package com.example.tikitaka_android.profile.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tikitaka_android.network.Result
import com.example.tikitaka_android.profile.data.MyProfileResponse
import com.example.tikitaka_android.profile.data.ProfileRepository
import com.example.tikitaka_android.profile.data.ProfileResponse
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {
    private val repository = ProfileRepository()
    val myProfileLiveData: MutableLiveData<MyProfileResponse> = MutableLiveData()
    val friendProfileLiveData : MutableLiveData<ProfileResponse> =  MutableLiveData()

    fun getMyProfile(){
        viewModelScope.launch {
            val result = repository.getMyProfile()

            if(result is Result.Success){
                getMyProfileSuccess(result)
            }else {
                Log.e("ProfileViewModel", "fail")
            }
        }
    }

    fun getFriendProfile(id: String){
        viewModelScope.launch {
            val result = repository.getFriendProfile(id)

            if(result is Result.Success) {

            }else {

            }
        }
    }

    private fun getMyProfileSuccess(result: Result.Success<MyProfileResponse>){
        if(result.code == 200) {
            val data = result.data
            if(data != null) {
                myProfileLiveData.postValue(data)
            }
        }
    }

    private fun getFriendProfileSuccess(result: Result.Success<ProfileResponse>){
        if(result.code == 200) {
            val data = result.data
            if(data != null) {
                friendProfileLiveData.postValue(data)
            }
        }
    }

}