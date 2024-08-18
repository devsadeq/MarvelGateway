package com.example.marvelgateway.data.remote.response.creator


import com.example.marvelgateway.data.remote.response.base.SummaryResponse
import com.example.marvelgateway.data.remote.response.base.ThumbnailResponse
import com.example.marvelgateway.data.remote.response.base.UrlResponse
import com.google.gson.annotations.SerializedName

data class CreatorResponse(
    @SerializedName("comics")
    val comics: SummaryResponse?,
    @SerializedName("events")
    val events: SummaryResponse?,
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("fullName")
    val fullName: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("middleName")
    val middleName: String?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("series")
    val series: SummaryResponse?,
    @SerializedName("stories")
    val stories: SummaryResponse?,
    @SerializedName("suffix")
    val suffix: String?,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse?,
    @SerializedName("urls")
    val urls: List<UrlResponse>?
)