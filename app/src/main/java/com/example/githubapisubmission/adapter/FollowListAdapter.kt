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


class FollowListAdapter(
    private val list: List<AdapterProperty?>,
    private val allowToDetailActivity:Boolean = false
) :
    RecyclerView.Adapter<FollowListAdapter.FollowListViewHolder>() {
    class FollowListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var username: TextView = itemView.findViewById(R.id.text_view_username)
        private var avatar: ImageView = itemView.findViewById(R.id.image_view_avatar)

        fun bind(list: com.example.githubapisubmission.adapter.AdapterProperty?) {
            if (list != null) {
                username.text = list.login
                Glide.with(itemView).load(list.avatarUrl).into(avatar)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return FollowListViewHolder(view)
    }


    override fun getItemCount(): Int {
        val count = list.size
        Log.d("COUNT", count.toString())
        val max = 30
        return if (count < max) count else max
    }

    override fun onBindViewHolder(holder: FollowListViewHolder, position: Int) {
        val context = holder.itemView.context
        val login = list[position]?.login.toString()
        val avatarUrl = list[position]?.avatarUrl.toString()

        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("data", Favorite(login, avatarUrl))

        holder.bind(list[position])
        if (allowToDetailActivity){
            holder.itemView.findViewById<ImageView>(R.id.image_view_avatar).setOnClickListener {
                context.startActivity(intent)
            }
            holder.itemView.findViewById<TextView>(R.id.text_view_username).setOnClickListener {
                context.startActivity(intent)
            }
        }
    }
}