package com.example.githubapisubmission.data.retrofit

import com.example.githubapisubmission.data.response.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getUser(@Query("q") userName:String ): Call<GithubResponse>
}