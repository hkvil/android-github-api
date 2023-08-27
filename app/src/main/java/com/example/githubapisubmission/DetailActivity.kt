package com.example.githubapisubmission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubapisubmission.adapter.SectionsPagerAdapter
import com.example.githubapisubmission.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setupTabLayout()

        setContentView(binding.root)


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