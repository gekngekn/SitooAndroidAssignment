package com.gekn.sitooandroidassignment.network.models

import com.google.gson.annotations.SerializedName

data class NetworkSite(
    @SerializedName("siteid")
    val siteid: String,
    @SerializedName("protocol")
    val protocol: String,
    @SerializedName("server")
    val server: String,
    @SerializedName("abspath")
    val abspath: String,
    @SerializedName("siteurl")
    val siteurl: String,
    @SerializedName("eshop")
    val eshop: Int
)
