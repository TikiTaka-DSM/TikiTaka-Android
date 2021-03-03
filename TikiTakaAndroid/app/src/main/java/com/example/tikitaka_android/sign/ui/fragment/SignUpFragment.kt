 package com.example.tikitaka_android.sign.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tikitaka_android.sign.data.SignUpRequest
import com.example.tikitaka_android.sign.ui.SignActivity
import com.example.tikitaka_android.sign.viewModel.SignUpViewModel
import com.example.tikitaka_android.databinding.FragmentSignUpBinding
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment() {
    private val viewModel: SignUpViewModel by viewModels()
    private var mBinding: FragmentSignUpBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sign_signUp_button.setOnClickListener {
            signUpCheck()
            //(activity as SignActivity).replaceFragment(LoginFragment())
        }

        sign_goLogin_button.setOnClickListener {
            (activity as SignActivity).replaceFragment(LoginFragment())
        }
    }

    private fun signUpCheck() {
        if (binding.signPasswordEditText.text.toString()
                .equals(binding.signPasswordCheckEditText.text.toString())
        ) {
            binding.signFailTextView.visibility = View.INVISIBLE

            var signUpRequest = SignUpRequest("", "", "")

            signUpRequest.id = binding.signIdEditText.text.toString()
            signUpRequest.name = binding.signNameEditText.text.toString()
            signUpRequest.password = binding.signPasswordEditText.text.toString()

            viewModel.signUp(signUpRequest)
        } else {
            binding.signFailTextView.visibility = View.VISIBLE
            binding.signFailTextView.text = "비밀번호가 일치하지 않습니다"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString("id", binding.signIdEditText.text.toString())
            putString("name", binding.signNameEditText.text.toString())
            putString("password", binding.signPasswordEditText.text.toString())
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                binding.signIdEditText.setText(getString("id"))
                binding.signNameEditText.setText(getString("name"))
                binding.signPasswordEditText.setText(getString("password"))
            }
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

}