package com.example.tikitaka_android.profile.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.tikitaka_android.home.ui.HomeActivity
import com.example.tikitaka_android.network.TikiTakaConnect
import com.example.tikitaka_android.profile.viewModel.ProfileViewModel
import com.example.tikitaka_android.databinding.FragmentMyProfileBinding
import com.example.tikitaka_android.profile.viewModel.MyProfileViewModel

class MyProfileFragment : Fragment() {
    private var mBinding: FragmentMyProfileBinding? = null
    private val binding get() = mBinding!!
    private val viewModel: MyProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMyProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.myProfileSettingButton.setOnClickListener {
            (activity as HomeActivity).replaceFragment(ModifyProfileFragment())
        }

        setProfile()
    }

    fun setProfile() {
        viewModel.setMyProfile()

        viewModel.myProfileLiveData.observe(viewLifecycleOwner, {
            binding.myProfileNameTextView.text = it.profileData.name
            binding.myProfileIdTextView.text = it.profileData.id
            binding.myProfileIntroTextView.text = it.profileData.statusMessage
            Glide.with(this).load(TikiTakaConnect.s3 + it.profileData.img).into(binding.myProfileProfileImageView)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}