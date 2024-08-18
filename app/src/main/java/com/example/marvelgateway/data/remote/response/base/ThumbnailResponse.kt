package com.example.marvelgateway.data.remote.response.base


import com.google.gson.annotations.SerializedName

data class ThumbnailResponse(
    @SerializedName("extension")
    val extension: String?,
    @SerializedName("path")
    val path: String?
)