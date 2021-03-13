package com.example.tikitaka_android.chat

import android.app.Activity
import android.util.Log
import com.example.tikitaka_android.chat.data.User
import com.example.tikitaka_android.chat.ui.ChatActivity
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONException

import org.json.JSONObject


class TikiTakaSocket {

    private val BASE_URL = "http://54.180.2.226:3100"
    val token =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTU0NTg1NzUsIm5iZiI6MTYxNTQ1ODU3NSwianRpIjoiMWU4YTM4NDgtODViMS00ZjAwLTkwY2EtZWNhYzQ5NDA0MTc4IiwiZXhwIjoxNjI0MDk4NTc1LCJpZGVudGl0eSI6Im1oIiwiZnJlc2giOmZhbHNlLCJ0eXBlIjoiYWNjZXNzIn0._-MLgrPb_2gti-PWLCOGKsVTfIOZ6a45oFefVEZCBLM"


    private var socket: Socket = IO.socket(BASE_URL)

    fun open() {
        socket.connect()
    }

    fun joinRoom(roomID: Int) {
        socket.emit("joinRoom", roomID)
    }

    fun sendMessage(roomID: Int, message: String) {
        socket.emit("sendMessage", roomID, token, message)
    }

    fun sendImage(roomID: Int, image: String) {
        socket.emit("sendImage", roomID, token, image)
    }

    fun close() {
        socket.disconnect()
    }

    fun getMessage() {
        socket.on("realTimeChatting", onUpdateChat)
    }

    var onUpdateChat = Emitter.Listener { args ->
        ChatActivity().runOnUiThread {
            Runnable {
                val data = args[0] as JSONObject
                val message: String?
                val photo: String?
                val createdAt: String

                try {
                    message = data.getString("message")
                    photo = data.getString("photo")
                    createdAt = data.getString("createdAt")

                    Log.e("socket","message: $message, photo: $photo, createdAt: $createdAt")
                } catch (e: JSONException) {
                    Log.e("socket",e.toString())
                    return@Runnable
                }
            }
        }

    }

}