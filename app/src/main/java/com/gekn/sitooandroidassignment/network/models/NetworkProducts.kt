package com.gekn.sitooandroidassignment.network.models

import com.google.gson.annotations.SerializedName

data class NetworkProducts(
    @SerializedName("totalcount")
    val totalcount: Int,
    @SerializedName("items")
    val items: List<NetworkProduct>

)