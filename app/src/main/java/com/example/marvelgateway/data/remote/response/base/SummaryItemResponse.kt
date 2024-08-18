package com.example.marvelgateway.data.remote.response.base


import com.google.gson.annotations.SerializedName

data class SummaryItemResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("type")
    val type: String?
)