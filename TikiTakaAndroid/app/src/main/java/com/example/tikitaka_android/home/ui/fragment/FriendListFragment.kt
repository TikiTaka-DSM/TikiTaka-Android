package com.example.tikitaka_android.home.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tikitaka_android.databinding.FragmentFriendListBinding
import com.example.tikitaka_android.home.ui.adapter.FriendListAdapter
import com.example.tikitaka_android.home.viewModel.HomeViewModel

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

        setFriendList()
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