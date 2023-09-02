package com.example.githubapisubmission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapisubmission.adapter.FollowListAdapter
import com.example.githubapisubmission.adapter.UserListAdapter
import com.example.githubapisubmission.data.viewmodel.FavoriteViewModel
import com.example.githubapisubmission.data.viewmodel.FavoriteViewModelFactory
import com.example.githubapisubmission.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var favoriteViewModel: FavoriteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Favorite Users"
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        recyclerView = binding.recyclerViewFavorite
        setupViewModel()
        setContentView(binding.root)
    }

    private fun setupViewModel() {
        favoriteViewModel = ViewModelProvider(
            this,
            FavoriteViewModelFactory(application)
        )[FavoriteViewModel::class.java]

        favoriteViewModel.getAllFavorites().observe(this){
            if (it!=null){
                recyclerView.adapter = FollowListAdapter(it,true)
            }
        }
    }

}