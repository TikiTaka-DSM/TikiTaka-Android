package com.example.tikitaka_android.Sign.Data

import com.example.tikitaka_android.Network.TikiTakaConnect
import retrofit2.Response

class SignRepository{

    suspend fun login(hashMap: HashMap<String,String>){
        return TikiTakaConnect.createAPI().login(hashMap)
    }

    suspend fun signUp(signUpRequest: SignUpRequest): Response<Unit> {
        return TikiTakaConnect.createAPI().signUp(signUpRequest)
    }
}