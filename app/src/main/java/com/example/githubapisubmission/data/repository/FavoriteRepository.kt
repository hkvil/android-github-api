package com.example.githubapisubmission.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.githubapisubmission.data.database.Favorite
import com.example.githubapisubmission.data.database.FavoriteDao
import com.example.githubapisubmission.data.database.FavoriteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

//PENGHUBUNG VIEWMODEL DENGAN DATABASE
//TODO,STOP DISINI DULU BELUM DAPAT ALURNYA
class FavoriteRepository(application: Application) {
    private val mFavoritesDao:FavoriteDao
    private val executorService:ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        mFavoritesDao = db.favoriteDao()
    }

    fun getAllFavorites():LiveData<List<Favorite>> = mFavoritesDao.getAllFavorites()

    fun insert(favorite: Favorite){
        executorService.execute{mFavoritesDao.insert(favorite)}
    }
    fun delete(favorite: Favorite){
        executorService.execute { mFavoritesDao.delete(favorite) }
    }
    fun update(favorite: Favorite){
        executorService.execute { mFavoritesDao.update(favorite) }
    }
}