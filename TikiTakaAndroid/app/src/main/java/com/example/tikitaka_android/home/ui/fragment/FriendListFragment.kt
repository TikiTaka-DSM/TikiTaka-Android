package com.example.tikitaka_android.home.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tikitaka_android.R
import com.example.tikitaka_android.databinding.FragmentFriendListBinding
import com.example.tikitaka_android.home.ui.HomeActivity
import com.example.tikitaka_android.home.ui.adapter.FriendListAdapter
import com.example.tikitaka_android.home.viewModel.HomeViewModel
import com.example.tikitaka_android.profile.ui.ProfileActivity

class FriendListFragment : Fragment() {
    private var mBinding: FragmentFriendListBinding? = null
    private val binding get() = mBinding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = FragmentFriendListBinding.inflate(inflater,container,false)

        binding.friendListRecyclerview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.friendListSearchButton.setOnClickListener {
            setFriendSearch()
        }

        setFriendList()
    }

    private fun setFriendSearch(){
        var friendId = binding.friendListSearchEditText.text.toString()

        if (friendId != null) {
            viewModel.myFriendSearch(friendId)
        }

        viewModel.mySearchLiveData.observe(viewLifecycleOwner, {
            if (it) {
                val intent = Intent(context, ProfileActivity::class.java)
                intent.putExtra("friendID",friendId)
                startActivity(intent)
            }
        })
    }

    private fun setFriendList(){
        viewModel.getFriendsList()

        viewModel.friendListLiveData.observe(viewLifecycleOwner, {
            var friendListAdapter = FriendListAdapter(it.friends)
            binding.friendListRecyclerview.adapter = friendListAdapter
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}