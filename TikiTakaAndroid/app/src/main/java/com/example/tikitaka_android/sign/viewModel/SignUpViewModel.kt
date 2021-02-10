package com.example.tikitaka_android.sign.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tikitaka_android.network.Result
import com.example.tikitaka_android.sign.data.SignRepository
import com.example.tikitaka_android.sign.data.SignUpRequest
import kotlinx.coroutines.launch

class SignUpViewModel: ViewModel() {
    private val repository = SignRepository()

    fun signUp(signUpRequest: SignUpRequest){
        viewModelScope.launch {
            when(val result = repository.signUp(signUpRequest)){
                is Result.Success -> {
                    if(result.code == 201){
                        Log.e("SignUpViewModel","signUp")
                    }
                }
                is Result.Error -> {
                    Log.e("SignUpViewModel",result.exception)
                }

            }
        }
    }
}