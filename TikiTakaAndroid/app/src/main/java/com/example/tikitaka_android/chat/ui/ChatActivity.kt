package com.example.tikitaka_android.chat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.example.tikitaka_android.chat.TikiTakaSocket
import com.example.tikitaka_android.chat.viewModel.ChatViewModel
import com.example.tikitaka_android.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {
    private var mBinding: ActivityChatBinding? = null
    private val binding get() = mBinding!!
    private val OPEN_GALLERY = 201
    private var socket = TikiTakaSocket()
    private val viewModel = ChatViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityChatBinding.inflate(layoutInflater)

        setContentView(binding.root)

        /*binding.chatImageImageButton.setOnClickListener {
            getImage()
            socket.sendImage(roomId,"test")
        }

        binding.chatSendButton.setOnClickListener {
            var message = binding.chatMessageEditText.text.toString()

            socket.sendMessage(roomId,message)
        }

        binding.chatRecordingImageButton.setOnClickListener {

        }*/

    }

    private fun getChatList(){

    }

    private fun getImage(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent,OPEN_GALLERY)
    }


    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}