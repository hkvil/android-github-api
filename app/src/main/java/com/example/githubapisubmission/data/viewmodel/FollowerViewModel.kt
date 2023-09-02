package com.example.githubapisubmission.data.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapisubmission.data.response.CommonResponse
import com.example.githubapisubmission.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel: ViewModel() {
    private val responseBodyFollowerList: MutableLiveData<List<CommonResponse?>> by lazy {
        MutableLiveData<List<CommonResponse?>>()
    }

    val readResponseBodyFollowerList: LiveData<List<CommonResponse?>>  get() = responseBodyFollowerList

    fun getFollowerListAPI(username: String,context:Context) {
        val client = ApiConfig.getApiService().getFollowerList(username)

        client.enqueue(object : Callback<List<CommonResponse>> {
            override fun onResponse(
                call: Call<List<CommonResponse>>,
                response: Response<List<CommonResponse>>
            ) {
                responseBodyFollowerList.value = response.body()?.toList()
            }

            override fun onFailure(call: Call<List<CommonResponse>>, t: Throwable) {
                val msg = "Error FollowerAPI : ${t.message}"
                Toast.makeText(context,msg, Toast.LENGTH_LONG).show()
            }


        })
    }
}