package com.example.marvelgateway.data.remote.response.event


import com.example.marvelgateway.data.remote.response.base.SummaryItemResponse
import com.example.marvelgateway.data.remote.response.base.SummaryResponse
import com.example.marvelgateway.data.remote.response.base.ThumbnailResponse
import com.example.marvelgateway.data.remote.response.base.UrlResponse
import com.google.gson.annotations.SerializedName

data class EventResponse(
    @SerializedName("characters")
    val characters: SummaryResponse?,
    @SerializedName("comics")
    val comics: SummaryResponse?,
    @SerializedName("creators")
    val creators: SummaryResponse?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("end")
    val end: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("next")
    val next: SummaryItemResponse?,
    @SerializedName("previous")
    val previous: SummaryItemResponse?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("series")
    val series: SummaryResponse?,
    @SerializedName("start")
    val start: String?,
    @SerializedName("stories")
    val stories: SummaryResponse?,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("urls")
    val urls: List<UrlResponse>?
)