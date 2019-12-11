package com.example.testretrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * A simple [Fragment] subclass.
 */
class profile : Fragment() {

     var user = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    interface GitHubService {
        @GET("/users/{username}")
        open fun getUser(@Path("username") user: String?): Call<ProfileData?>?
    }
    interface OnNetworkCallbackListener {
        fun onResponse(user: ProfileData?, retrofit: Retrofit?)
        fun onBodyError(responseBodyError: ResponseBody?)
        fun onBodyErrorIsNull()
        fun onFailure(t: Throwable?)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var userLogin = view?.findViewById<TextView>(R.id.textView2)
        user = arguments?.getString("user_name_bundle").toString()
        userLogin?.setText("@"+arguments?.getString("user_name_bundle").toString())
        NetworkConnectionManager().callServer(
            networkCallbackListener,
            user
        )


    }

    var networkCallbackListener: OnNetworkCallbackListener = object : OnNetworkCallbackListener {
        override fun onResponse(user: ProfileData?, retrofit: Retrofit?) { //200
            userName_git.setText(user?.name)
            Picasso.get().load(user?.avatarUrl).into(user_image)
            var M = ""+user?.bio+"\n"+user?.location+"\n"+user?.html_url
            //textView3.setText(user?.followersUrl)
            textView5.setText(M)
            textView3.setText(user?.followersUrl)
        }

        override fun onBodyError(responseBodyError: ResponseBody?) { //404 (error not null)
        }

        override fun onBodyErrorIsNull() { //404 (error is null)
        }

        override fun onFailure(t: Throwable?) { //fail any course
        }
    }
}




