package com.example.tikitaka_android.Sign.Ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tikitaka_android.R
import com.example.tikitaka_android.Sign.ViewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_sign.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_sign_up.*

class LoginFragment : Fragment() {
    val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        login_login_button.setOnClickListener {
            var id = sign_id_editText.text.toString()
            var password = sign_password_editText.text.toString()

            viewModel.login(id,password)
        }

        login_notUser_button.setOnClickListener {

        }
    }

}