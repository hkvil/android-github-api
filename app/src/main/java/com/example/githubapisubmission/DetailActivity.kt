package com.example.githubapisubmission

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.githubapisubmission.adapter.SectionsPagerAdapter
import com.example.githubapisubmission.data.database.Favorite
import com.example.githubapisubmission.data.response.DetailUserResponse
import com.example.githubapisubmission.data.viewmodel.DetailViewModel
import com.example.githubapisubmission.data.viewmodel.FavoriteViewModel
import com.example.githubapisubmission.data.viewmodel.FavoriteViewModelFactory
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
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var favorite: Favorite
    private var isFavorite: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        supportActionBar?.title = "Detail User"
        //TODO,Null karena limit API,hadeh susah2
        favorite = intent.getParcelableExtra("data")!!
        username = favorite.login

        favoriteViewModel = ViewModelProvider(
            this,
            FavoriteViewModelFactory(application)
        )[FavoriteViewModel::class.java]
        favoriteViewModel.isFavorited(username).observe(this) {
            isFavorite = it
            if (isFavorite > 0) {
                binding.fab.setImageResource(R.drawable.baseline_favorite_24)
            } else if (isFavorite == 0) {
                binding.fab.setImageResource(R.drawable.baseline_favorite_border_24)
            }
        }

        lifecycleScope.launch {
            getAPI()
            setupData()
        }
        setupFAB()
        setupTabLayout()
        setContentView(binding.root)
    }

    private fun setupFAB() {
        binding.fab.setOnClickListener {
            if (isFavorite > 0) {
                Toast.makeText(this, "REMOVED FROM FAVORITES", Toast.LENGTH_LONG).show()
                favoriteViewModel.removeFromFavorite(favorite)
            } else if (isFavorite == 0) {
                Toast.makeText(this, "ADDED TO FAVORITES", Toast.LENGTH_LONG).show()
                favoriteViewModel.addToFavorite(favorite)
            }
        }
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
        detailViewModel.getDetailUserAPI(username, this)
        followingViewModel.getFollowingListAPI(username, this)
        followerViewModel.getFollowerListAPI(username, this)
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

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

}