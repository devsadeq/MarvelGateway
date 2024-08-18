package com.example.marvelgateway.data.remote.response.comic


import com.example.marvelgateway.data.remote.response.base.SummaryResponse
import com.example.marvelgateway.data.remote.response.base.ThumbnailResponse
import com.example.marvelgateway.data.remote.response.base.UrlResponse
import com.google.gson.annotations.SerializedName

data class ComicResponse(
    @SerializedName("comics")
    val comics: SummaryResponse?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("events")
    val events: SummaryResponse?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("title")
    val name: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("series")
    val series: SummaryResponse?,
    @SerializedName("stories")
    val stories: SummaryResponse?,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse?,
    @SerializedName("urls")
    val urls: List<UrlResponse>?
)