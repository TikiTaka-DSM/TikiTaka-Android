package com.example.tikitaka_android.profile.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tikitaka_android.network.Result
import com.example.tikitaka_android.profile.data.MyProfileData
import com.example.tikitaka_android.profile.data.ProfileRepository
import kotlinx.coroutines.launch

class MyProfileViewModel: ViewModel() {
    private val repository =  ProfileRepository()
    val myProfileLiveData: MutableLiveData<MyProfileData> = MutableLiveData()

    fun setMyProfile(){
        viewModelScope.launch {
            val result = repository.getMyProfile()

            if (result is Result.Success) {
                getProfileSuccess(result)
            } else {
                Log.e("myProfileViewModel", (result as Result.Error).exception)
            }
        }
    }

    private fun getProfileSuccess(result: Result.Success<MyProfileData>) {
        if(result.code == 200){
            val data = result.data
            if(data != null){
                myProfileLiveData.postValue(data)
            }
        }
    }
}