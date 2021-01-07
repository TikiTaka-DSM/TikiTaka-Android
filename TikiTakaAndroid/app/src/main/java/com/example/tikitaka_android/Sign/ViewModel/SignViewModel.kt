package com.example.tikitaka_android.Sign.ViewModel

import androidx.lifecycle.ViewModel
import com.example.tikitaka_android.Sign.Data.SignRepository
import com.example.tikitaka_android.Sign.Data.SignUpRequest

class SignViewModel : ViewModel() {
    private val repository = SignRepository()

    fun login(id: String, password: String){
        val hashMap: HashMap<String,String> = HashMap()
        hashMap["id"] = id
        hashMap["password"] = password
    }

    fun signUp(signUpRequest: SignUpRequest){

    }

}