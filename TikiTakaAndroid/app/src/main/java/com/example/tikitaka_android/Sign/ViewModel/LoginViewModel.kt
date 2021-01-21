package com.example.tikitaka_android.Sign.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tikitaka_android.Network.Result
import com.example.tikitaka_android.Sign.Data.SignRepository
import com.example.tikitaka_android.Sign.Data.TokenResponse
import com.example.tikitaka_android.Util.TikiTakaApplication.Companion.prefs
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
                    if(result.code == 200){
                        getAuthSuccess(result.data)
                    }
                }
                
                is Result.Error -> {

                }
            }
        }

    }

    private fun getAuthSuccess(data: TokenResponse){
        prefs?.saveToken(true,data.accessToken)
    }

}