package com.example.tikitaka_android.sign.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tikitaka_android.sign.ui.SignActivity
import com.example.tikitaka_android.sign.viewModel.LoginViewModel
import com.example.tikitaka_android.databinding.FragmentLoginBinding
import com.example.tikitaka_android.home.ui.HomeActivity
import kotlinx.android.synthetic.main.fragment_login.*

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
            (activity as SignActivity).replaceFragment(SignUpFragment())
        }

    }

    private fun loginCheck() {
        var id = binding.loginIdEditText.text.toString()
        var password = binding.loginPasswordEditText.text.toString()

        viewModel.login(id, password)

        if(viewModel.loginLiveData.value == true) {
            changeActivity()
        } else {
            binding.loginLoginfailTextView.visibility = View.VISIBLE
        }
    }

    private fun changeActivity(){
        (activity as SignActivity).changeActivity()
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}