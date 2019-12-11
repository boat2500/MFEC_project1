package com.example.testretrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class MyData (
    @SerializedName("login")
    @Expose
    var login: String? = null,
    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String? = null
)
