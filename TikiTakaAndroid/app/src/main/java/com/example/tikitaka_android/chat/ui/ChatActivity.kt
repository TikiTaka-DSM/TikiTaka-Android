package com.example.tikitaka_android.chat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tikitaka_android.chat.TikiTakaSocket
import com.example.tikitaka_android.chat.data.RoomData
import com.example.tikitaka_android.chat.ui.adapter.ChatListAdapter
import com.example.tikitaka_android.chat.viewModel.ChatAPIViewModel
import com.example.tikitaka_android.databinding.ActivityChatBinding
import okio.ByteString.Companion.encode
import java.lang.Exception

class ChatActivity : AppCompatActivity() {
    private var mBinding: ActivityChatBinding? = null
    private val binding get() = mBinding!!
    private val viewModel = ChatAPIViewModel()
    private val OPEN_GALLERY = 200
    private var socket = TikiTakaSocket()
    private var roomId: Int = 0
    private lateinit var roomData: RoomData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chatInit()

        binding.chatImageImageButton.setOnClickListener{
            getImage()
        }

        binding.chatSendButton.setOnClickListener {
            sendMessage()
        }
    }

    private fun chatInit() {
        val friendId = intent.getStringExtra("friendId").toString()
        if(intent.hasExtra("roomId")) {
            roomId = intent.getIntExtra("roomId",0)
            Log.e("ChatActivity","friendId : $friendId, roomId : $roomId")
        } else {
            viewModel.joinRoom(friendId)
            viewModel.joinRoomLiveData.observe(this, {
                roomId = it
            })
        }

        viewModel.joinRoom(friendId)
        socket.open()
        socket.joinRoom(roomId)

        setChatList()
    }

    private fun setChatList(){
        viewModel.getChatList(roomId)

        viewModel.chatListLiveData.observe(this,{
            binding.chatNameTextView.text = it.roomData.name
            roomData = it.roomData

            var chatListAdapter = ChatListAdapter(it)
            binding.chatListRecycler.layoutManager = LinearLayoutManager(this)
            binding.chatListRecycler.adapter = chatListAdapter
        })
    }

    private fun sendMessage() {
        var message = binding.chatMessageEditText.text.toString()
        socket.sendMessage(roomId,message)

        binding.chatMessageEditText.text.clear()
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