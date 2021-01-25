package com.example.tikitaka_android.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tikitaka_android.profile.viewModel.ProfileViewModel
import com.example.tikitaka_android.databinding.FragmentModifyProfileBinding

class ModifyProfileFragment : Fragment() {
    private var mBinding: FragmentModifyProfileBinding? = null
    private val binding get() = mBinding!!
    private val viewModel: ProfileViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentModifyProfileBinding.inflate(inflater,container,false)

        var name= "min"

        return binding.root
    }

    private fun getProfile(){
        viewModel.getMyProfile()

        viewModel.myProfileLiveData.observe(viewLifecycleOwner, {

        })
    }

}