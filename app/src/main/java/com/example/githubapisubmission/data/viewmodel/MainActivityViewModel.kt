package com.example.githubapisubmission.data.viewmodel

import android.content.Context
import android.widget.Toast
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

    fun getUsersAPI(username: String,context: Context) {
        val client = ApiConfig.getApiService().getUser(username)

        client.enqueue(object : Callback<UsersResponse> {
            override fun onResponse(
                call: Call<UsersResponse>,
                response: Response<UsersResponse>
            ) {
                responseBodyMain.value = response.body()
                if (readResponseBoydMain.value?.totalCount == 0){
                    Toast.makeText(context,"NO USER FOUND",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                val msg = "Error Search Users : ${t.message}"
                showErrorToast(context,msg)
            }

        })
    }
    fun showErrorToast(context: Context,msg:String){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
    }
}