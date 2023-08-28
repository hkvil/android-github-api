package com.example.githubapisubmission.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapisubmission.data.response.DetailUserResponse
import com.example.githubapisubmission.data.response.FollowersListResponse
import com.example.githubapisubmission.data.response.FollowingListResponse
import com.example.githubapisubmission.data.response.GithubResponse
import com.example.githubapisubmission.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResponseViewModel : ViewModel() {
    //SEARCH -> FOTO,USERNAME
    private val responseBodyMain: MutableLiveData<GithubResponse?> by lazy {
        MutableLiveData<GithubResponse?>()
    }
    val readResponseBoydMain: LiveData<GithubResponse?> get() = responseBodyMain

    private val lastQuery: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


    fun getUsersAPI(username: String) {
        val client = ApiConfig.getApiService().getUser(username)

        client.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {

                responseBodyMain.value = response.body()
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}