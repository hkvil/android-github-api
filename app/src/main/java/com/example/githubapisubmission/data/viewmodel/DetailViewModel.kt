package com.example.githubapisubmission.data.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapisubmission.data.response.DetailUserResponse
import com.example.githubapisubmission.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel()  {

    private val responseBodyDetailUser: MutableLiveData<DetailUserResponse?> by lazy {
        MutableLiveData<DetailUserResponse?>()
    }
    val readResponseBodyDetailUser: LiveData<DetailUserResponse?> get() = responseBodyDetailUser



    fun getDetailUserAPI(username: String,context:Context) {
        val client = ApiConfig.getApiService().getDetailUser(username)

        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                responseBodyDetailUser.value = response.body()
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                val msg = "Error Detail User : ${t.message}"
                Toast.makeText(context,msg, Toast.LENGTH_LONG).show()
            }

        })
    }
}