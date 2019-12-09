package com.example.testretrofit

import com.example.testretrofit.profile.GitHubService
import com.example.testretrofit.profile.OnNetworkCallbackListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkConnectionManager {

    fun callServer(listener: OnNetworkCallbackListener?, username: String?) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val git = retrofit.create(GitHubService::class.java)
        val call = git.getUser(username)
        call?.enqueue(object : Callback<ProfileData?> {
            fun onResponse(response: Response<ProfileData?>?, retrofit: Retrofit?) {}
            fun onFailure(t: Throwable?) {}

            override fun onResponse(call: Call<ProfileData?>, response: Response<ProfileData?>) {
                val user: ProfileData? = response.body()

                if (user == null) {
                    val responseBody = response.errorBody()
                    if (responseBody != null) {
                        listener!!.onBodyError(responseBody)
                    } else {
                        listener!!.onBodyErrorIsNull()
                    }
                } else {
                    listener!!.onResponse(user, retrofit)
                }
            }

            override fun onFailure(call: Call<ProfileData?>, t: Throwable) {
                listener?.onFailure(t)
            }
        })
    }
}