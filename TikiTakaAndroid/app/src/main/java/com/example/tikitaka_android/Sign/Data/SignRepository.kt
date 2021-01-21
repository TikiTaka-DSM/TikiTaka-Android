package com.example.tikitaka_android.Sign.Data

import com.example.tikitaka_android.Base.BaseRepository
import com.example.tikitaka_android.Network.Result
import com.example.tikitaka_android.Network.TikiTakaConnect

class SignRepository : BaseRepository(){

    suspend fun login(hashMap: HashMap<String,String>): Result<TokenResponse>{
        return mappingToResult { TikiTakaConnect.createAPI().login(hashMap) }
    }

    suspend fun signUp(signUpRequest: SignUpRequest): Result<Unit> {
        return mappingToResult { TikiTakaConnect.createAPI().signUp(signUpRequest) }
    }
}