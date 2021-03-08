package com.example.tikitaka_android.chat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tikitaka_android.R
import com.example.tikitaka_android.chat.TikiTakaSocket
import com.example.tikitaka_android.chat.ui.adapter.ChatListAdapter
import com.example.tikitaka_android.chat.viewModel.ChatAPIViewModel
import com.example.tikitaka_android.chat.viewModel.ChatSocketVIewModel
import com.example.tikitaka_android.databinding.ActivityChatBinding
import okio.ByteString.Companion.encode
import java.lang.Exception

class ChatActivity : AppCompatActivity() {
    private var mBinding: ActivityChatBinding? = null
    private val binding get() = mBinding!!
    private val viewModel = ChatAPIViewModel()
    private val sViewModel = ChatSocketVIewModel()
    private val OPEN_GALLERY = 200
    private var socket = TikiTakaSocket()
    private var roomId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityChatBinding.inflate(layoutInflater)

        setContentView(binding.root)

        chatInit()
        //setChatList()

        binding.chatImageImageButton.setOnClickListener{
            getImage()
        }

        binding.chatSendButton.setOnClickListener {
            Log.e("chatActivity",roomId.toString())

            var message = binding.chatMessageEditText.text.toString()
            socket.sendMessage(roomId,message)

            Log.e("ChatActivity","sendMessage")
        }

    }

    private fun chatInit() {
        val friendId = intent.getStringExtra("friendId").toString()
        if(intent.hasExtra("roomId")) {
            roomId = intent.getIntExtra("roomId",0)
        } else {
            viewModel.joinRoom(friendId)
            viewModel.joinRoomLiveData.observe(this, {
                roomId = it
            })
        }

        viewModel.joinRoom(friendId)
        sViewModel.open()
        sViewModel.joinRoom(roomId)
    }

    private fun setChatList(){
        viewModel.getChatList(roomId)

        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.reverseLayout = true
        mLayoutManager.stackFromEnd = true

        viewModel.chatListLiveData.observe(this,{
            binding.chatNameTextView.text = it.roomData.name

            var chatListAdapter = ChatListAdapter(it)
            binding.chatListRecycler.layoutManager = mLayoutManager
            binding.chatListRecycler.adapter = chatListAdapter
        })
    }

    private fun getImage(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent,OPEN_GALLERY)
    }

    override fun startActivityForResult(data: Intent?, requestCode: Int) {
        super.startActivityForResult(data, requestCode)

        if(requestCode == OPEN_GALLERY && requestCode == RESULT_OK) {
            try{
                var image = data?.data
                var path: String = Base64.encodeToString(image?.encodedAuthority?.encode()?.toByteArray(),Base64.DEFAULT)

                socket.sendImage(roomId,path)

            }catch (e: Exception){
                println(e)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        socket.close()
        mBinding = null
    }
}