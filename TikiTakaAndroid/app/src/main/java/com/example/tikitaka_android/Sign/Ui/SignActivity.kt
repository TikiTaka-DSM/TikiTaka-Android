package com.example.tikitaka_android.Sign.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tikitaka_android.R
import com.example.tikitaka_android.Sign.Ui.Fragment.LoginFragment
import com.example.tikitaka_android.Sign.Ui.Fragment.SignUpFragment

class SignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        setFragment("login")
    }

    open fun setFragment(fragment: String){
        when(fragment){
            "signUp" -> {
                supportFragmentManager.beginTransaction().replace(R.id.sign_fragment, SignUpFragment()).commit()
            }

            "login" -> {
                supportFragmentManager.beginTransaction().replace(R.id.sign_fragment,LoginFragment()).commit()
            }
        }
    }
}