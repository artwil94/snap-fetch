package com.example.snapfetch.domain.model

import com.example.snapfetch.data.remote.dtos.PhotoDto

data class Photo(
    val id: String? = null,
    val author: String = "",
    val width: Int? = null,
    val height: Int? = null,
    val url: String = "",
    val downloadUrl: String = ""
)

fun PhotoDto.toEntity(): Photo {
    return Photo(
        id = id ?: "",
        author = author ?: "",
        width = width,
        height = height,
        url = downloadUrl ?: "",
        downloadUrl = url ?: ""
    )
}