package com.example.tikitaka_android.chat.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.tikitaka_android.R
import com.example.tikitaka_android.chat.data.ChatListData
import com.example.tikitaka_android.chat.data.MessageData

class ChatListAdapter(private val chatListData: ChatListData): RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val friendProfile = itemView.findViewById<ImageView>(R.id.chatCard_friendImage_imageView)


        fun bind(messageData: MessageData){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_cardview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(chatListData.messageData[position])

    override fun getItemCount(): Int = chatListData.messageData.size
}