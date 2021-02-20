package com.example.tikitaka_android.profile.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.tikitaka_android.databinding.FragmentModifyProfileBinding
import com.example.tikitaka_android.home.ui.HomeActivity
import com.example.tikitaka_android.network.TikiTakaConnect
import com.example.tikitaka_android.profile.viewModel.MyProfileViewModel
import java.io.File
import java.lang.Exception

class ModifyProfileFragment : Fragment() {
    private var mBinding: FragmentModifyProfileBinding? = null
    private val binding get() = mBinding!!
    private val viewModel: MyProfileViewModel by viewModels()
    private val OPEN_GALLERY = 200
    private var imageFile: File? = null
    private var imageState = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentModifyProfileBinding.inflate(inflater, container, false)

        init()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.modifyProfileLayout.setOnClickListener {
            getImage()
        }

        binding.modifyChangeButton.setOnClickListener {
            Log.e("ModifyProfile", "ChangeBtn Click")

            var name = binding.modifyNameEditText.text.toString()
            var stateMessage = binding.modifyIntroEditText.text.toString()

            viewModel.modifyProfile(imageState, imageFile, name, stateMessage)
        }
    }

    private fun init(){
        getProfileData()

        imageFile = File(Environment.getExternalStorageDirectory(), "profile.png")
    }

    private fun getImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, OPEN_GALLERY)
    }

    override fun startActivityForResult(data: Intent?, requestCode: Int) {
        super.startActivityForResult(data, requestCode)

        if (requestCode == OPEN_GALLERY && requestCode == AppCompatActivity.RESULT_OK) {
            try {
                imageFile = File(data?.data?.path)
                imageState = true
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    private fun getProfileData() {
        viewModel.setMyProfile()

        viewModel.myProfileLiveData.observe(viewLifecycleOwner, {
            binding.modifyNameEditText.setText(it.profileData.name)
            binding.modifyIntroEditText.setText(it.profileData.statusMessage)
            Glide.with(this).load(TikiTakaConnect.s3 + it.profileData.img)
                .into(binding.modifyProfileImageView)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

}