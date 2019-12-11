package com.example.testretrofit


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_title.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class title : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_title, container, false)
    }
interface GitHubService {
    @GET("/users?since=0")
    open fun getUser(): Call<List<MyData?>>?
}
    interface OnNetworkCallbackListener {
        fun onResponse(user: List<MyData?>?, retrofit: Retrofit?)
        fun onFailure(t: Throwable?)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callServer(
            networkCallbackListener
        )
    }
    var networkCallbackListener: OnNetworkCallbackListener = object : OnNetworkCallbackListener {
        override fun onResponse(user: List<MyData?>?, retrofit: Retrofit?) {
            mylist.adapter = ListAdapter(title(), user as List<MyData>)
        }
        override fun onFailure(t: Throwable?) { //fail any course
        }
    }
    fun callServer(listener: title.OnNetworkCallbackListener?) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val git = retrofit.create(title.GitHubService::class.java)
        val call = git.getUser()
        call?.enqueue(object : Callback<List<MyData?>> {
            fun onResponse(response: Response <List<MyData?>>?, retrofit: Retrofit?) {}
            fun onFailure(t: Throwable?) {}

            override fun onResponse(call: Call<List<MyData?>>, response: Response<List<MyData?>>) {
                val user: List<MyData?>? = response.body()

                if (user != null) {
                    listener!!.onResponse(user, retrofit)
                }
            }
            override fun onFailure(call: Call<List<MyData?>>, t: Throwable) {
                listener?.onFailure(t)
            }
        })
    }
}

