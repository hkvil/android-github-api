package com.example.githubapisubmission.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapisubmission.DetailActivity
import com.example.githubapisubmission.R
import com.example.githubapisubmission.data.database.Favorite
import com.example.githubapisubmission.data.response.ItemsItem
import com.example.githubapisubmission.data.response.UsersResponse

class UserListAdapter(private val list: UsersResponse?) :
    RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {
    class UserListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var username: TextView = itemView.findViewById(R.id.text_view_username)
        private var avatar: ImageView = itemView.findViewById(R.id.image_view_avatar)

        fun bind(list: ItemsItem?) {
            if (list != null) {
                username.text = list.login
                Glide.with(itemView).load(list.avatarUrl).into(avatar)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserListViewHolder(view)
    }

    override fun getItemCount(): Int {
        val count = list?.totalCount!!
        val max = 30
        return if (count < max) count else max
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val context = holder.itemView.context
        val login = list?.items?.get(position)?.login.toString()
        val avatarUrl = list?.items?.get(position)?.avatarUrl.toString()
        holder.bind(list?.items?.get(position))
        holder.itemView.findViewById<ImageView>(R.id.image_view_avatar).setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("data",Favorite(login,avatarUrl))
            context.startActivity(intent)
        }
        holder.itemView.findViewById<TextView>(R.id.text_view_username).setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("data",Favorite(login, avatarUrl))
            context.startActivity(intent)
        }
    }
}