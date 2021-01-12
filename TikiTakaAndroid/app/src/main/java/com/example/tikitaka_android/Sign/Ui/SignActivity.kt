package com.example.tikitaka_android.Sign.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tikitaka_android.R
import com.example.tikitaka_android.Sign.Ui.Fragment.LoginFragment

class SignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        supportFragmentManager.beginTransaction().replace(R.id.sign_fragment,LoginFragment()).commit()
    }

    fun changeFragment(){

    }
}