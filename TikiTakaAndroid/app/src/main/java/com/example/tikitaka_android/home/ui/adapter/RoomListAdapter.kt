package com.example.tikitaka_android.home.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tikitaka_android.R
import com.example.tikitaka_android.chat.ui.ChatActivity
import com.example.tikitaka_android.home.data.Room
import com.example.tikitaka_android.network.TikiTakaConnect

class RoomListAdapter(private val roomList: List<Room>): RecyclerView.Adapter<RoomListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val profileImage = itemView.findViewById<ImageView>(R.id.room_profile_imageView)
        val name = itemView.findViewById<TextView>(R.id.room_name_textView)
        val lastChat = itemView.findViewById<TextView>(R.id.room_lastChat_textView)

        fun bind(room: Room){
            name.text = room.user.name
            lastChat.text = room.lastMessage
            Glide.with(itemView).load(TikiTakaConnect.s3+room.user.img).circleCrop().into(profileImage)

            itemView.setOnClickListener {
                Log.e("RoomListAdapter", "friendId : ${room.user.id}, roomId: ${room.roomId}")
                val intent = Intent(itemView.context, ChatActivity::class.java)
                intent.putExtra("friendId", room.user.id)
                intent.putExtra("roomId",room.roomId)
                ContextCompat.startActivity(itemView.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_room,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  = holder.bind(roomList[position])

    override fun getItemCount(): Int = roomList.size
}