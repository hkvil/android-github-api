package com.example.githubapisubmission.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.githubapisubmission.adapter.AdapterProperty
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Favorite(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "login")
    override var login: String,
    //var username: String,

    @ColumnInfo(name = "avatarUrl")
    override var avatarUrl: String,
    //var avatarURL:String

    ):Parcelable,AdapterProperty