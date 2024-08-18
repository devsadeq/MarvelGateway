package com.example.marvelgateway.data.remote.response.base


import com.google.gson.annotations.SerializedName

data class SummaryResponse(
    @SerializedName("available")
    val available: String?,
    @SerializedName("collectionURI")
    val collectionURI: String?,
    @SerializedName("items")
    val items: List<SummaryItemResponse?>?,
    @SerializedName("returned")
    val returned: String?
)