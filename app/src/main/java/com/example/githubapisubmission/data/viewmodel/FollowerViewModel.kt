package com.example.githubapisubmission.data.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapisubmission.data.response.FollowListResponse
import com.example.githubapisubmission.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel: ViewModel() {
    private val responseBodyFollowerList: MutableLiveData<List<FollowListResponse?>> by lazy {
        MutableLiveData<List<FollowListResponse?>>()
    }

    val readResponseBodyFollowerList: LiveData<List<FollowListResponse?>>  get() = responseBodyFollowerList

    fun getFollowerListAPI(username: String,context:Context) {
        val client = ApiConfig.getApiService().getFollowerList(username)

        client.enqueue(object : Callback<List<FollowListResponse>> {
            override fun onResponse(
                call: Call<List<FollowListResponse>>,
                response: Response<List<FollowListResponse>>
            ) {
                responseBodyFollowerList.value = response.body()?.toList()
            }

            override fun onFailure(call: Call<List<FollowListResponse>>, t: Throwable) {
                val msg = "Error FollowerAPI : ${t.message}"
                Toast.makeText(context,msg, Toast.LENGTH_LONG).show()
            }


        })
    }
}