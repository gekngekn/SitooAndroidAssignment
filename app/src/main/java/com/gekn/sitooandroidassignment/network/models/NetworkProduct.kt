package com.gekn.sitooandroidassignment.network.models

import com.google.gson.annotations.SerializedName

data class NetworkProduct(
    @SerializedName("productid")
    var productid: Int,
    @SerializedName("moneyprice")
    var moneyprice: String,
    @SerializedName("title")
    var title: String,

)