package com.example.githubapisubmission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.githubapisubmission.adapter.SectionsPagerAdapter
import com.example.githubapisubmission.data.response.GithubResponse
import com.example.githubapisubmission.data.retrofit.ApiConfig
import com.example.githubapisubmission.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setupTabLayout()
        testAPI()
        setContentView(binding.root)


    }

    private fun testAPI() {
        val client = ApiConfig.getApiService().getUser("hkvil")
        client.enqueue(object: Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    Log.d("TESAPI", responseBody?.items?.get(0).toString())
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
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