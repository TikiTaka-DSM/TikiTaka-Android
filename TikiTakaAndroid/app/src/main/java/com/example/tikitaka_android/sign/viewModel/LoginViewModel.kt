package com.example.tikitaka_android.sign.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tikitaka_android.network.Result
import com.example.tikitaka_android.sign.data.SignRepository
import com.example.tikitaka_android.sign.data.TokenResponse
import com.example.tikitaka_android.util.TikiTakaApplication.Companion.prefs
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val repository = SignRepository()

    private val _loginLiveData = MutableLiveData<Boolean>()
    val loginLiveData: LiveData<Boolean> get() = _loginLiveData

    fun login(id: String, password: String) = viewModelScope.launch {
        val hashMap: HashMap<String, String> = HashMap()
        hashMap["id"] = id
        hashMap["password"] = password

        viewModelScope.launch {
            when(val result = repository.login(hashMap)){
                is Result.Success -> {
                    if(result.code == 200){
                        setLoginLiveData(true)
                        getAuthSuccess(result.data)
                    }
                }

                is Result.Error -> {
                    setLoginLiveData(false)
                }
            }
        }
    }

    private fun setLoginLiveData(value: Boolean){
        _loginLiveData.value = value
    }

    private fun getAuthSuccess(data: TokenResponse){
        prefs?.saveToken(true,data.accessToken)
    }

}