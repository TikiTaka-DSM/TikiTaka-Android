package com.example.tikitaka_android.Sign.Ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tikitaka_android.R
import com.example.tikitaka_android.Sign.Ui.SignActivity
import com.example.tikitaka_android.Sign.ViewModel.LoginViewModel
import com.example.tikitaka_android.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_sign_up.*

class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()
    private var mBinding: FragmentLoginBinding? = null
    private val binding get() = mBinding!!


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        login_login_button.setOnClickListener {
            loginCheck()
        }

        login_goSign_button.setOnClickListener {
            (activity as SignActivity).setFragment("signUp")
        }

    }

    private fun loginCheck() {
        var id = binding.loginIdEditText.text.toString()
        var password = binding.loginPasswordEditText.text.toString()

        viewModel.login(id, password)
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}