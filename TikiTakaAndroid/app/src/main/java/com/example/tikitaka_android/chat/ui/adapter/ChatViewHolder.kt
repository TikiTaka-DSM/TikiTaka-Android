package com.example.tikitaka_android.chat.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tikitaka_android.R
import com.example.tikitaka_android.chat.data.MessageData
import com.example.tikitaka_android.network.TikiTakaConnect

class ChatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val friendProfile = itemView.findViewById<ImageView>(R.id.chatCard_friendProfile_imageView)
    private val friendChat = itemView.findViewById<TextView>(R.id.chatCard_friendChat_textView)
    private val friendImage = itemView.findViewById<ImageView>(R.id.chatCard_friendImage_imageView)
    private val myChat = itemView.findViewById<TextView>(R.id.chatCard_myChat_textView)
    private val myImage = itemView.findViewById<ImageView>(R.id.chatCard_myImage_imageView)

    fun bind(messageData: MessageData, friendName: String){
        if(friendName != messageData.user.name) {
            mySelectMessage(messageData.message, messageData.photo)
        } else {
            friendSelectMessage(messageData.message, messageData.photo, messageData.user.img)
        }
    }

    private fun friendSelectMessage(message: String?, photo: String?, img: String) {
        if(message != null) {
            friendChat.visibility = View.VISIBLE
            friendChat.text = message
        } else if(photo != null) {
            friendImage.visibility = View.VISIBLE
            Glide.with(itemView).load(TikiTakaConnect.s3 + photo).into(friendImage)
        }

        friendProfile.visibility = View.VISIBLE
        Glide.with(itemView).load(TikiTakaConnect.s3 + img).into(friendProfile)
    }

    private fun mySelectMessage(message: String?, photo: String?) {
        if(message != null) {
            myChat.visibility = View.VISIBLE
            myChat.text = message
        } else if(photo != null) {
            myImage.visibility = View.VISIBLE
            Glide.with(itemView).load(TikiTakaConnect.s3 + photo).into(myImage)
        }
    }

}