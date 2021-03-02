package com.example.tikitaka_android.home.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tikitaka_android.R
import com.example.tikitaka_android.home.viewModel.HomeViewModel
import com.example.tikitaka_android.databinding.FragmentSearchBinding
import com.example.tikitaka_android.home.data.HomeRepository
import com.example.tikitaka_android.home.ui.HomeActivity
import com.example.tikitaka_android.network.TikiTakaConnect
import com.example.tikitaka_android.profile.ui.ProfileActivity

class SearchFragment : Fragment() {
    private var mBinding: FragmentSearchBinding? = null
    private val binding get() = mBinding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.searchButton.setOnClickListener {
            setFriendSearch()
        }
    }

    private fun setFriendSearch() {
        var friendId = binding.searchEditText.text.toString()

        if (friendId != null) {
            viewModel.searchFriend(friendId)
        } else {
            binding.searchCommentTextView.text = getString(R.string.search_friend)
        }

        viewModel.searchLiveData.observe(viewLifecycleOwner, {
            if (it) {
                val intent = Intent(context,ProfileActivity::class.java)
                intent.putExtra("friendID",friendId)
                startActivity(intent)

            } else {
                binding.searchCommentTextView.text = getString(R.string.notExist_id)
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}