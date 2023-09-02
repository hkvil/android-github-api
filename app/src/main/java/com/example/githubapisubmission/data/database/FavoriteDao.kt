package com.example.githubapisubmission.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

//QUERY HERE
@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: Favorite)

    @Update
    fun update(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)

    @Query("SELECT * FROM Favorite")
    fun getAllFavorites():LiveData<List<Favorite>>

    @Query("SELECT COUNT(*) FROM Favorite WHERE login = :username")
    fun isUserFavorited(username:String):LiveData<Int>
}