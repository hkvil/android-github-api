package com.example.githubapisubmission

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.githubapisubmission.adapter.SectionsPagerAdapter
import com.example.githubapisubmission.data.response.DetailUserResponse
import com.example.githubapisubmission.data.viewmodel.DetailViewModel
import com.example.githubapisubmission.data.viewmodel.FollowerViewModel
import com.example.githubapisubmission.data.viewmodel.FollowingViewModel
import com.example.githubapisubmission.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var username: String
    private val detailViewModel: DetailViewModel by viewModels()
    private val followerViewModel: FollowerViewModel by viewModels()
    private val followingViewModel: FollowingViewModel by viewModels()
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
        showLoading(true)
        val dataObserver = Observer<DetailUserResponse?> {
            binding.textViewName.text = it?.name ?: "No Name"
            binding.textViewUsername.text = it?.login
            Glide.with(this).load(it?.avatarUrl).into(binding.imageViewProfil)
            binding.textViewFollowers.text = "${it?.followers} Followers"
            binding.textViewFollowing.text = "${it?.following} Following"
            showLoading(false)
        }
        detailViewModel.readResponseBodyDetailUser.observe(this, dataObserver)

    }

    private fun getAPI() {
        detailViewModel.getDetailUserAPI(username,this)
        followingViewModel.getFollowingListAPI(username,this)
        followerViewModel.getFollowerListAPI(username,this)
    }


    private fun setupTabLayout() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        val tabs = binding.tabLayout
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = if (position == 0) "Follower" else "Following"
        }.attach()

        supportActionBar?.elevation = 0f
    }

    private fun showLoading(isLoading:Boolean){
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

}