package com.example.tikitaka_android.profile.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tikitaka_android.network.Result
import com.example.tikitaka_android.profile.data.MyProfileData
import com.example.tikitaka_android.profile.data.ProfileRepository
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class MyProfileViewModel: ViewModel() {
    private val repository =  ProfileRepository()
    private val _myProfileLiveData = MutableLiveData<MyProfileData>()
    val myProfileLiveData: LiveData<MyProfileData> get() = _myProfileLiveData

    fun setMyProfile() {
        viewModelScope.launch {
            val result = repository.getMyProfile()

            if (result is Result.Success) {
                getProfileSuccess(result)
            } else {
                Log.e("myProfileViewModel", (result as Result.Error).exception)
            }
        }
    }

    fun modifyProfile(imageState: Boolean, image: File?, name: String, stateMessage: String){
        var imagePart = getImagePart(imageState, image)
        val namePart = MultipartBody.Part.createFormData("name",name)
        val messagePart = MultipartBody.Part.createFormData("statusMessage", stateMessage)

        viewModelScope.launch {
            repository.modifyProfile(imagePart,namePart,messagePart)
        }

    }

    private fun getImagePart(state: Boolean, image: File?): MultipartBody.Part {
        var imagePart: MultipartBody.Part? = null

        if(state) {
            imagePart = MultipartBody.Part.createFormData(
                    "img",
                    image!!.name,
                    image!!.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            )
        } else {
            imagePart = MultipartBody.Part.createFormData("img"," ")
        }

        Log.e("viewModel","Trans get Image Part")

        return imagePart

    }

    private fun getProfileSuccess(result: Result.Success<MyProfileData>) {
        if(result.code == 200){
            val data = result.data
            if(data != null){
                _myProfileLiveData.value = result.data
            }
        }
    }
}