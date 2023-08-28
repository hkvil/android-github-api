package com.example.githubapisubmission.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapisubmission.R
import com.example.githubapisubmission.data.response.GithubResponse
import com.example.githubapisubmission.data.response.ItemsItem

class UserListAdapter(private val list: GithubResponse?) :
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
        //Log.d("COUNT", list?.totalCount!!.toString())
        val COUNT = list?.totalCount!!
        val MAX   = 30
        return if (COUNT < MAX) COUNT else MAX
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        //Log.d("OnBind", list?.items?.get(position).toString())
        holder.bind(list?.items?.get(position))
    }
}