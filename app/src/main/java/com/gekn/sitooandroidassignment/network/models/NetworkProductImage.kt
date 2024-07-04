package com.gekn.sitooandroidassignment.network.models

import com.google.gson.annotations.SerializedName

data class NetworkProductImage(
    @SerializedName("resourceid")
    val resourceid: String,
    @SerializedName("mimetype")
    val mimetype: String,
    @SerializedName("width")
    val width: Long,
    @SerializedName("height")
    val height: Long,
    @SerializedName("filesize")
    val filesize: Long,
    @SerializedName("datecreated")
    val datecreated: Long
)