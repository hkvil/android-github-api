package com.example.githubapisubmission.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Favorite(
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "avatarURL")
    var avatarURL:String

    ):Parcelable