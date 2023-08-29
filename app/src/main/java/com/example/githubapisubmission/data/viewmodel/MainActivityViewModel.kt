package com.example.githubapisubmission.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapisubmission.data.response.UsersResponse
import com.example.githubapisubmission.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    private val responseBodyMain: MutableLiveData<UsersResponse?> by lazy {
        MutableLiveData<UsersResponse?>()
    }
    val readResponseBoydMain: LiveData<UsersResponse?> get() = responseBodyMain


    fun getUsersAPI(username: String) {
        val client = ApiConfig.getApiService().getUser(username)

        client.enqueue(object : Callback<UsersResponse> {
            override fun onResponse(
                call: Call<UsersResponse>,
                response: Response<UsersResponse>
            ) {

                responseBodyMain.value = response.body()
            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                Log.e("Error", "FollowerListAPI failed: ${t.message}")
            }

        })
    }
}