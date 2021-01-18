package com.example.tikitaka_android.Sign.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tikitaka_android.Network.Result
import com.example.tikitaka_android.Sign.Data.SignRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val repository = SignRepository()

    fun login(id: String, password: String){
        val hashMap: HashMap<String,String> = HashMap()
        hashMap["id"] = id
        hashMap["password"] = password

        viewModelScope.launch {
            repository.login(hashMap)

            when(val result = repository.login(hashMap)){
                is Result.Success -> {

                }

                is Result.Error -> {

                }
            }
        }

    }

    private fun getAuthSuccess(result: Result.Success<Unit>){
        if(result.code == 200){

        }
    }

}