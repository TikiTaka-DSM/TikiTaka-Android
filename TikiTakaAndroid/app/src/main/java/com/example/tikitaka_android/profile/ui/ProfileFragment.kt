package com.example.tikitaka_android.profile.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.tikitaka_android.R
import com.example.tikitaka_android.chat.ui.ChatActivity
import com.example.tikitaka_android.databinding.FragmentProfileBinding
import com.example.tikitaka_android.profile.viewModel.ProfileViewModel

class ProfileFragment : Fragment() {
    private var mBinding: FragmentProfileBinding? = null
    private val binding get() = mBinding!!
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var friendID: String
    private var roomId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        friendID = arguments?.getString("friendID").toString()
        roomId = arguments?.getInt("roomID")
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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

        viewModel.friendProfileLiveData.observe(viewLifecycleOwner, {
            friendID = it.profileData.id
            roomId = it.roomData.roomId

            binding.profileNameTextView.text = it.profileData.name
            binding.profileIntroTextView.text = it.profileData.statusMessage

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

        viewModel.blockLiveData.observe(viewLifecycleOwner, {
            when (it) {
                404 -> Toast.makeText(context, getString(R.string.notExist_id), Toast.LENGTH_SHORT).show()

                409 -> Toast.makeText(context, getString(R.string.already_block), Toast.LENGTH_SHORT).show()

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

        viewModel.addFriendLiveData.observe(viewLifecycleOwner, {
            when (it) {
                200 -> {
                    binding.profileAddUserButton.visibility = View.INVISIBLE
                    binding.profileBlockButton.visibility = View.VISIBLE
                    binding.profileChatButton.visibility = View.VISIBLE
                }
                409 -> Toast.makeText(context, getString(R.string.already_friend), Toast.LENGTH_SHORT).show()

                400 -> Toast.makeText(context, getString(R.string.myself_request), Toast.LENGTH_SHORT).show()

                else -> Toast.makeText(context, getString(R.string.unknown_request), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun clickedChat() {
        val intent = Intent(context, ChatActivity::class.java)
        intent.putExtra("roomId", roomId)
        startActivity(intent)
    }

}