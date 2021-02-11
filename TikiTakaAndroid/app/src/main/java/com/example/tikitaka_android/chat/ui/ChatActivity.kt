package com.example.tikitaka_android.chat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.example.tikitaka_android.chat.MediaRecorder
import com.example.tikitaka_android.chat.TikiTakaSocket
import com.example.tikitaka_android.chat.ui.adapter.ChatListAdapter
import com.example.tikitaka_android.chat.viewModel.ChatViewModel
import com.example.tikitaka_android.databinding.ActivityChatBinding
import okio.ByteString.Companion.encode
import java.io.File
import java.lang.Exception

class ChatActivity : AppCompatActivity() {
    private var mBinding: ActivityChatBinding? = null
    private val binding get() = mBinding!!
    private val viewModel = ChatViewModel()
    private val OPEN_GALLERY = 200
    private var socket = TikiTakaSocket()
    private var recordingCount = 0
    private var roomID: Int = 21

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityChatBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.chatImageImageButton.setOnClickListener {
            getImage()
        }

        binding.chatSendButton.setOnClickListener {
            var message = binding.chatMessageEditText.text.toString()

            Log.e("ChatActivity","sendMessage")
        }

        binding.chatRecordingImageButton.setOnClickListener {

            Log.e("ChatActivity","joinRoom")

            /*recordingCount++
            recording()*/
        }
    }

    private fun setChatList(){
        viewModel.getChatList(roomID)

        viewModel.chatListLiveData.observe(this,{
            var chatListAdapter = ChatListAdapter(it)
            binding.chatListRecycler.adapter = chatListAdapter
        })
    }

    private fun recording(){
        val recording = MediaRecorder
        var voiceFile = File(Environment.getExternalStorageDirectory(), "TikiTaka.mp3")

        if(recordingCount % 2 == 0){
            recordingView(true)
            recording.startRecorder(voiceFile.absolutePath)
        } else {
            recordingView(false)
            recording.stopRecorder()

            socket.sendVoice(1,voiceFile.absolutePath)
        }
    }

    private fun recordingView(value: Boolean){
        if(value){
            binding.chatImageImageButton.visibility = View.GONE
            binding.chatMessageEditText.visibility = View.GONE
            binding.chatRecordingMessageTextView.visibility = View.VISIBLE
        } else {
            binding.chatImageImageButton.visibility = View.VISIBLE
            binding.chatMessageEditText.visibility = View.VISIBLE
            binding.chatRecordingMessageTextView.visibility = View.INVISIBLE
        }
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

                socket.sendImage(roomID,path)

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