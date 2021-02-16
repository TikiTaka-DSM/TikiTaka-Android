package com.example.tikitaka_android.profile.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.tikitaka_android.R
import com.example.tikitaka_android.chat.ui.ChatActivity
import com.example.tikitaka_android.databinding.ActivityProfileBinding
import com.example.tikitaka_android.network.TikiTakaConnect
import com.example.tikitaka_android.profile.viewModel.ProfileViewModel

class ProfileActivity : AppCompatActivity() {
    private var mBinding: ActivityProfileBinding? = null
    private val binding get() = mBinding!!
    private val viewModel = ProfileViewModel()
    private lateinit var friendID: String
    private var roomId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("friendID")){
            friendID = intent.getStringExtra("friendID").toString()
        } else Toast.makeText(this,getString(R.string.unknown_request), Toast.LENGTH_SHORT).show()

        setProfile()

        binding.profileAddUserButton.setOnClickListener {
            addFriend()
        }

        binding.profileBlockButton.setOnClickListener {
            clickedBlock()
        }

        binding.profileChatButton.setOnClickListener {
            clickedChat()
        }
    }

    private fun setProfile() {
        viewModel.getFriendProfile(friendID)

        viewModel.friendProfileLiveData.observe(this, {
            roomId = it.roomData.roomId

            binding.profileNameTextView.text = it.profileData.name
            binding.profileIntroTextView.text = it.profileData.statusMessage
            Glide.with(this).load(TikiTakaConnect.s3+it.profileData.img).into(binding.profileProfileImageView)

            if (it.state.friend) {
                if (it.state.block) {
                    binding.profileAddUserButton.run {
                        visibility = View.VISIBLE
                        text = getString(R.string.unblock)
                    }
                } else {
                    binding.profileChatButton.visibility = View.VISIBLE
                    binding.profileBlockButton.visibility = View.VISIBLE
                }
            } else {
                binding.profileAddUserButton.visibility = View.VISIBLE
            }
        })
    }

    private fun clickedBlock() {
        viewModel.setBlock(friendID)

        viewModel.blockLiveData.observe(this, {
            when (it) {
                404 -> Toast.makeText(this, getString(R.string.notExist_id), Toast.LENGTH_SHORT).show()

                409 -> Toast.makeText(this, getString(R.string.already_block), Toast.LENGTH_SHORT).show()

                200 -> {
                    binding.profileAddUserButton.visibility = View.INVISIBLE
                    binding.profileChatButton.visibility = View.INVISIBLE
                    binding.profileBlockButton.run {
                        visibility = View.VISIBLE
                        text = getString(R.string.unblock)
                    }
                }
            }
        })
    }

    private fun addFriend() {
        viewModel.addFriend(friendID)

        viewModel.addFriendLiveData.observe(this, {
            when (it) {
                200 -> {
                    binding.profileAddUserButton.visibility = View.INVISIBLE
                    binding.profileBlockButton.visibility = View.VISIBLE
                    binding.profileChatButton.visibility = View.VISIBLE
                }
                409 -> Toast.makeText(this, getString(R.string.already_friend), Toast.LENGTH_SHORT).show()

                400 -> Toast.makeText(this, getString(R.string.myself_request), Toast.LENGTH_SHORT).show()

                else -> Toast.makeText(this, getString(R.string.unknown_request), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun clickedChat() {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra("roomId", roomId)
        startActivity(intent)
    }
}