package com.example.snapfetch.data.remote.dtos

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoDto(
    @SerializedName("id") val id: String? = null,
    @SerializedName("author") val author: String? = null,
    @SerializedName("width") val width: Int? = null,
    @SerializedName("height") val height: Int? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("download_url") val downloadUrl: String? = null
)