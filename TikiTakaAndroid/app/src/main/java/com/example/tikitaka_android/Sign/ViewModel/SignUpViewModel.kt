package com.example.tikitaka_android.Sign.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tikitaka_android.Network.Result
import com.example.tikitaka_android.Sign.Data.SignRepository
import com.example.tikitaka_android.Sign.Data.SignUpRequest
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