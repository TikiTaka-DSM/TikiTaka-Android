package com.example.tikitaka_android.profile.viewModel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tikitaka_android.network.Result
import com.example.tikitaka_android.profile.data.MyProfileData
import com.example.tikitaka_android.profile.data.ProfileRepository
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okio.ByteString.Companion.toByteString
import java.io.File

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

    fun modifyProfile(imageState: Boolean, image: File?, name: String, stateMessage: String){
        var imagePart = getImagePart(imageState, image)
        val namePart = MultipartBody.Part.createFormData("name",name)
        val messagePart = MultipartBody.Part.createFormData("statusMessage", stateMessage)

        Log.e("viewModel", "modifyProfile")
        viewModelScope.launch {
            repository.modifyProfile(imagePart,namePart,messagePart)
        }

    }

    private fun getImagePart(state: Boolean, image: File?): MultipartBody {
        var imagePart: RequestBody

        imagePart = if(state) {
            MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("img",image!!.name,RequestBody.create(
                    MultipartBody.FORM, image!!))
                .build()
        } else {
            MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("img","")
                .build()
        }

        Log.e("viewModel","Trans get Image Part")

        return imagePart
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