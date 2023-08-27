package com.example.githubapisubmission.data.retrofit

import com.example.githubapisubmission.data.response.DetailUserResponse
import com.example.githubapisubmission.data.response.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")//Main
    fun getUser(@Query("q") userName:String ): Call<GithubResponse>
    @GET("/users/{username}")//Detail After Main Clicked
    fun getDetailUser(@Path("username") userName: String) : Call<DetailUserResponse>
}