package com.example.tikitaka_android.sign.data

import com.example.tikitaka_android.base.BaseRepository
import com.example.tikitaka_android.network.Result
import com.example.tikitaka_android.network.TikiTakaConnect

class SignRepository : BaseRepository(){

    suspend fun login(hashMap: HashMap<String,String>): Result<TokenResponse>{
        return mappingToResult { TikiTakaConnect.createAPI().login(hashMap) }
    }

    suspend fun signUp(signUpRequest: SignUpRequest): Result<Unit> {
        return mappingToResult { TikiTakaConnect.createAPI().signUp(signUpRequest) }
    }
}