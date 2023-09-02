package com.example.githubapisubmission.data.retrofit

import com.example.githubapisubmission.data.response.DetailUserResponse
import com.example.githubapisubmission.data.response.CommonResponse
import com.example.githubapisubmission.data.response.UsersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    //Ga dipake karena sama aja limitnya,apa saya ga tau

    @GET("search/users")//Main
    @Headers("Authorization:token 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")
    fun getUser(@Query("q") userName:String ): Call<UsersResponse>
    @GET("/users/{username}")//Detail After Main Clicked
    @Headers("Authorization:token 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")
    fun getDetailUser(@Path("username") userName: String) : Call<DetailUserResponse>
    @GET("/users/{username}/followers")
    @Headers("Authorization:token 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")
    fun getFollowerList(@Path("username") userName: String) : Call<List<CommonResponse>>
    @GET("/users/{username}/following")
    @Headers("Authorization:token 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")
    fun getFollowingList(@Path("username") userName: String) : Call<List<CommonResponse>>
}
