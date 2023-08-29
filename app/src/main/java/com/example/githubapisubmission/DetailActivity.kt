package com.example.githubapisubmission

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.githubapisubmission.adapter.SectionsPagerAdapter
import com.example.githubapisubmission.data.viewmodel.SharedViewModel
import com.example.githubapisubmission.data.response.DetailUserResponse
import com.example.githubapisubmission.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var username:String
    private val viewModel: SharedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        supportActionBar?.title = "Detail User"
        username = intent.getStringExtra("username").toString()
        lifecycleScope.launch {
            getAPI()
            setupData()
        }
        setupTabLayout()
        setContentView(binding.root)
    }

    @SuppressLint("SetTextI18n")
    private fun setupData() {
        val dataObserver = Observer<DetailUserResponse?>{
            binding.textViewName.text = it?.name
            binding.textViewUsername.text = it?.login
            Glide.with(this).load(it?.avatarUrl).into(binding.imageViewProfil)
            binding.textViewFollowers.text = "${it?.followers} Followers"
            binding.textViewFollowing.text = "${it?.following} Following"
        }
        viewModel.readResponseBodyDetailUser.observe(this,dataObserver)

    }

    private fun getAPI() {
        viewModel.getDetailUserAPI(username)
        viewModel.getFollowerListAPI(username)
        viewModel.getFollowingListAPI(username)
    }


    private fun setupTabLayout() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        val tabs = binding.tabLayout
        TabLayoutMediator(tabs,viewPager){tab,position->
            tab.text = if(position == 0 ) "Follower" else "Following"
        }.attach()

        supportActionBar?.elevation = 0f
    }
}