package com.example.marvelgateway.data.remote.response.base


import com.google.gson.annotations.SerializedName

data class PaginationDataResponse<T>(
    @SerializedName("count")
    val count: String?,
    @SerializedName("limit")
    val limit: String?,
    @SerializedName("offset")
    val offset: String?,
    @SerializedName("results")
    val results: List<T>?,
    @SerializedName("total")
    val total: String?
)