package com.example.tikitaka_android.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tikitaka_android.databinding.FragmentModifyProfileBinding

class ModifyProfileFragment : Fragment() {
    private var mBinding: FragmentModifyProfileBinding? = null
    private val binding get() = mBinding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentModifyProfileBinding.inflate(inflater,container,false)

        return binding.root
    }

}