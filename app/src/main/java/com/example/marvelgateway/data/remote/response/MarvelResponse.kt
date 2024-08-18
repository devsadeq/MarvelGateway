package com.example.marvelgateway.data.remote.response


import com.example.marvelgateway.data.remote.response.base.PaginationDataResponse
import com.google.gson.annotations.SerializedName

data class MarvelResponse<T>(
    @SerializedName("attributionHTML")
    val attributionHTML: String?,
    @SerializedName("attributionText")
    val attributionText: String?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("data")
    val `data`: PaginationDataResponse<T>?,
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("status")
    val status: String?,
)