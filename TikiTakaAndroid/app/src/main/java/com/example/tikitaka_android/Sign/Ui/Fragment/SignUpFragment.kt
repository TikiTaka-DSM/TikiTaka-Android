package com.example.tikitaka_android.Sign.Ui.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tikitaka_android.R
import com.example.tikitaka_android.Sign.Data.SignUpRequest
import com.example.tikitaka_android.Sign.ViewModel.SignViewModel
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment() {
    private val viewModel: SignViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sign_signUp_button.setOnClickListener {
            loginAfterCheck()
        }

        sign_goLogin_button.setOnClickListener {

        }
    }

    private fun loginAfterCheck() {
        if (sign_password_editText.text.toString()
                .equals(sign_passwordCheck_editText.text.toString())
        ) {
            var signUpRequest = SignUpRequest("", "", "")

            signUpRequest.id = sign_id_editText.text.toString()
            signUpRequest.name = sign_name_editText.text.toString()
            signUpRequest.password = sign_password_editText.text.toString()

            viewModel.signUp(signUpRequest)
        } else {
            sign_fail_textView.text = "비밀번호가 일치하지 않습니다."
        }
    }

}