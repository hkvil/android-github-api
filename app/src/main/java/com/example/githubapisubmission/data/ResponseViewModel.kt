package com.example.githubapisubmission.data

import androidx.lifecycle.ViewModel
import com.example.githubapisubmission.data.response.DetailUserResponse
import com.example.githubapisubmission.data.response.GithubResponse
import com.example.githubapisubmission.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResponseViewModel : ViewModel() {
    //SEARCH -> FOTO,USERNAME
    private var responseBodyMain: GithubResponse? = null
    private var userFoundCount: Int? = null
    //DETAIL -> FOTO,USERNAME,NAMA,JUMLAH FOLLOWER,JUMLAH FOLLOWING
    private var responseBodyDetail : DetailUserResponse? = null

    fun getUsers(username: String) {
        val client = ApiConfig.getApiService().getUser(username)

        client.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                responseBodyMain = response.body()
                userFoundCount = responseBodyMain?.totalCount

            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getDetailUser(username: String){
        val client = ApiConfig.getApiService().getDetailUser(username)

        client.enqueue(object : Callback<DetailUserResponse>{
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                responseBodyDetail = response.body()
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}