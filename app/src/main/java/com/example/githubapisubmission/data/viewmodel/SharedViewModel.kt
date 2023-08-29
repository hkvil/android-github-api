package com.example.githubapisubmission.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapisubmission.data.response.DetailUserResponse
import com.example.githubapisubmission.data.response.FollowListResponse
import com.example.githubapisubmission.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SharedViewModel : ViewModel() {

    //Create Instance in DetailActivity,Fragment use ViewModel from DetailActivity

    private val responseBodyDetailUser: MutableLiveData<DetailUserResponse?> by lazy {
        MutableLiveData<DetailUserResponse?>()
    }
    val readResponseBodyDetailUser: LiveData<DetailUserResponse?> get() = responseBodyDetailUser

    private val responseBodyFollowerList: MutableLiveData<List<FollowListResponse?>> by lazy {
        MutableLiveData<List<FollowListResponse?>>()
    }

    val readResponseBodyFollowerList: LiveData<List<FollowListResponse?>> get() = responseBodyFollowerList

    private val responseBodyFollowingList: MutableLiveData<List<FollowListResponse?>> by lazy {
        MutableLiveData<List<FollowListResponse?>>()
    }

    val readResponseBodyFollowingList: LiveData<List<FollowListResponse?>> get() = responseBodyFollowingList


    fun getFollowingListAPI(username: String) {
        val client = ApiConfig.getApiService().getFollowingList(username)

        client.enqueue(object : Callback<List<FollowListResponse>> {
            override fun onResponse(
                call: Call<List<FollowListResponse>>,
                response: Response<List<FollowListResponse>>
            ) {
                responseBodyFollowingList.value = response.body()?.toList()

            }

            override fun onFailure(call: Call<List<FollowListResponse>>, t: Throwable) {
                Log.e("Error", "FollowerListAPI failed: ${t.message}")
            }

        })
    }

    fun getFollowerListAPI(username: String) {
        val client = ApiConfig.getApiService().getFollowerList(username)

        client.enqueue(object : Callback<List<FollowListResponse>> {
            override fun onResponse(
                call: Call<List<FollowListResponse>>,
                response: Response<List<FollowListResponse>>
            ) {
                responseBodyFollowerList.value = response.body()?.toList()
            }

            override fun onFailure(call: Call<List<FollowListResponse>>, t: Throwable) {
                Log.e("Error", "FollowerListAPI failed: ${t.message}")
            }


        })
    }

    fun getDetailUserAPI(username: String) {
        val client = ApiConfig.getApiService().getDetailUser(username)

        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                responseBodyDetailUser.value = response.body()
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                Log.e("Error", "FollowerListAPI failed: ${t.message}")
            }

        })
    }


}