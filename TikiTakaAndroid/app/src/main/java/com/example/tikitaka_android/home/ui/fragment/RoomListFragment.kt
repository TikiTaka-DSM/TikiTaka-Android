package com.example.tikitaka_android.home.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tikitaka_android.R
import com.example.tikitaka_android.databinding.FragmentRoomListBinding
import com.example.tikitaka_android.home.ui.adapter.RoomListAdapter
import com.example.tikitaka_android.home.viewModel.HomeViewModel

class RoomListFragment : Fragment() {
    private var mBinding: FragmentRoomListBinding? = null
    private val binding get() = mBinding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        mBinding = FragmentRoomListBinding.inflate(inflater, container, false)

        binding.roomListRecyclerview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRoomList()
    }

    private fun setRoomList(){
        viewModel.getRoomList()

        viewModel.roomListLiveData.observe(viewLifecycleOwner,{
            var roomListAdapter = RoomListAdapter(it.rooms)
            binding.roomListRecyclerview.adapter = roomListAdapter
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}