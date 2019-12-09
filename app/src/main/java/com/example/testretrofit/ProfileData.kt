package com.example.testretrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ProfileData(

    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String? = null,
    @SerializedName("followers_url")
    @Expose
    var followersUrl: String? = null,
    @SerializedName("following_url")
    @Expose
    var followingUrl: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("blog")
    @Expose
    var blog: String? = null,
    @SerializedName("location")
    @Expose
    var location: String? = null,
    @SerializedName("bio")
    @Expose
    var bio: Any? = null,
    @SerializedName("html_url")
    @Expose
    var html_url: String?=null

)


