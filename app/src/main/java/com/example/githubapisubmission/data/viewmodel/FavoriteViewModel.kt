package com.example.githubapisubmission.data.viewmodel

import android.app.Application
import android.view.animation.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubapisubmission.data.database.Favorite
import com.example.githubapisubmission.data.repository.FavoriteRepository

class FavoriteViewModel(application: Application):ViewModel() {
    private val mFavoriteViewModel:FavoriteRepository = FavoriteRepository(application)

    fun getAllFavorites():LiveData<List<Favorite>> = mFavoriteViewModel.getAllFavorites()
    fun isFavorited(username:String):LiveData<Int> = mFavoriteViewModel.isUserFavorited(username)
}