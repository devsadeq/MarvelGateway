package com.example.marvelgateway.data.remote.response.base


import com.google.gson.annotations.SerializedName

data class UrlResponse(
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
)