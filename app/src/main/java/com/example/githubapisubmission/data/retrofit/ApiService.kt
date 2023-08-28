package com.example.githubapisubmission.data.retrofit

import com.example.githubapisubmission.data.response.DetailUserResponse
import com.example.githubapisubmission.data.response.FollowersListResponse
import com.example.githubapisubmission.data.response.FollowingListResponse
import com.example.githubapisubmission.data.response.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers("Authorization: token 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")
    @GET("search/users")//Main
    fun getUser(@Query("q") userName:String ): Call<GithubResponse>
    @GET("/users/{username}")//Detail After Main Clicked
    fun getDetailUser(@Path("username") userName: String) : Call<DetailUserResponse>
    @GET("/users/{username}/followers")
    fun getFollowerList(@Path("username") userName: String) : Call<FollowersListResponse>
    @GET("/users/{username}/following")
    fun getFollowingList(@Path("username") userName: String) : Call<FollowingListResponse>
}