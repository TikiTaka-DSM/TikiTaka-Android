package com.example.tikitaka_android.home.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tikitaka_android.R
import com.example.tikitaka_android.chat.data.User
import com.example.tikitaka_android.network.TikiTakaConnect

class FriendListAdapter(private val friendList: List<User>): RecyclerView.Adapter<FriendListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val profileImage = itemView.findViewById<ImageView>(R.id.friend_profile_imageView)
        val name = itemView.findViewById<TextView>(R.id.friend_name_textView)

        fun bind(friend: User) {
            name.text = friend.name
            Glide.with(itemView).load(TikiTakaConnect.s3+friend.img).into(profileImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_friend,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(friendList[position])

    override fun getItemCount(): Int = friendList.size
}