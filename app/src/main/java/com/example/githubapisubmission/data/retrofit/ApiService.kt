package com.example.githubapisubmission.data.retrofit

import com.example.githubapisubmission.BuildConfig
import com.example.githubapisubmission.data.response.DetailUserResponse
import com.example.githubapisubmission.data.response.CommonResponse
import com.example.githubapisubmission.data.response.UsersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")//Main
    @Headers("Authorization:token "+BuildConfig.KEY)
    fun getUser(@Query("q") userName:String ): Call<UsersResponse>
    @GET("/users/{username}")//Detail After Main Clicked
    @Headers("Authorization:token "+BuildConfig.KEY)
    fun getDetailUser(@Path("username") userName: String) : Call<DetailUserResponse>
    @GET("/users/{username}/followers")
    @Headers("Authorization:token "+BuildConfig.KEY)
    fun getFollowerList(@Path("username") userName: String) : Call<List<CommonResponse>>
    @GET("/users/{username}/following")
    @Headers("Authorization:token "+BuildConfig.KEY)
    fun getFollowingList(@Path("username") userName: String) : Call<List<CommonResponse>>
}

