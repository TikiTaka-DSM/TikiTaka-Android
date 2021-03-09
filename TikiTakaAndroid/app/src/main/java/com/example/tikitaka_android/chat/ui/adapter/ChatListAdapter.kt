package com.example.tikitaka_android.chat.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tikitaka_android.R
import com.example.tikitaka_android.chat.data.ChatListData
import com.example.tikitaka_android.chat.data.MessageData
import com.example.tikitaka_android.network.TikiTakaConnect

class ChatListAdapter(private val chatListData: ChatListData): RecyclerView.Adapter<ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_cardview, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) = holder.bind(chatListData.messageData[position],chatListData.roomData.name)

    override fun getItemCount(): Int = chatListData.messageData.size
}