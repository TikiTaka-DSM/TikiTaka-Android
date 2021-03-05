package com.example.tikitaka_android.chat

import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter

class TikiTakaSocket {

    private val BASE_URL = "http://54.180.2.226:3000"
    val token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTEzNzk4MzAsIm5iZiI6MTYxMTM3OTgzMCwianRpIjoiMWM4NGVjMTQtMDAwZC00MGFhLWI4MDctODlhOGU4YTZjY2Q1IiwiZXhwIjoxNjIwMDE5ODMwLCJpZGVudGl0eSI6Im1oIiwiZnJlc2giOmZhbHNlLCJ0eXBlIjoiYWNjZXNzIn0.3KUVMIqr9TbAuNuJGM2vYvDm1tbNua55jAJxqC4jRMA"

    private var socket: Socket = IO.socket(BASE_URL)

    fun open() {
        socket.connect()
    }

    fun joinRoom(roomID: Int) {
        socket.emit("joinRoom", roomID)
    }

    fun sendMessage(roomID: Int, message: String) {
        if (message != null) {
            socket.emit("sendMessage", roomID, token, message)
        }
        close()
    }

    fun sendImage(roomID: Int, image: String) {
        if (image != null) {
            socket.emit("sendImage", roomID, token, image)
        }
    }

    fun close() {
        socket.disconnect()
    }

    fun getMessage() {
        socket.on("realTimeChatting", onUpdateChat)
    }

    var onUpdateChat = Emitter.Listener {
        val result = if(it.isNotEmpty()) it[0].toString() else it.toString()
    }

}