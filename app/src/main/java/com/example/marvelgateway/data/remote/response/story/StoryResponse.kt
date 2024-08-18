package com.example.marvelgateway.data.remote.response.story


import com.example.marvelgateway.data.remote.response.base.SummaryItemResponse
import com.example.marvelgateway.data.remote.response.base.SummaryResponse
import com.example.marvelgateway.data.remote.response.base.ThumbnailResponse
import com.google.gson.annotations.SerializedName

data class StoryResponse(
    @SerializedName("characters")
    val characters: SummaryResponse?,
    @SerializedName("comics")
    val comics: SummaryResponse?,
    @SerializedName("creators")
    val creators: SummaryResponse?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("events")
    val events: SummaryResponse?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("originalissue")
    val originalissue: SummaryItemResponse?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("series")
    val series: SummaryResponse?,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?
)