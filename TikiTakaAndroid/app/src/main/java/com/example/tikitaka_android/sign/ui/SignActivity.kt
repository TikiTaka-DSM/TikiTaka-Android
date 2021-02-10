package com.example.tikitaka_android.sign.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tikitaka_android.R
import com.example.tikitaka_android.home.ui.HomeActivity
import com.example.tikitaka_android.sign.ui.fragment.LoginFragment

class SignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        replaceFragment(LoginFragment())
    }

    fun changeActivity(){
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }

    fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.sign_fragment, fragment)
        fragmentTransaction.commit()
    }
}