package com.example.tikitaka_android.sign.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tikitaka_android.R
import com.example.tikitaka_android.home.ui.HomeActivity
import com.example.tikitaka_android.sign.ui.SignActivity
import com.example.tikitaka_android.util.TikiTakaApplication.Companion.prefs

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(prefs?.getToken() != null) {
            val intent = Intent(context,HomeActivity::class.java)
            startActivity(intent)
        } else {
            (activity as SignActivity).replaceFragment(LoginFragment())
        }
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


}