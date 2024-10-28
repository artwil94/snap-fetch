package com.example.snapfetch.data.remote

import com.example.snapfetch.data.remote.dtos.PhotoDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotosApi {

    @GET("v2/list")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<PhotoDto>

    @GET("/id/{id}/info")
    suspend fun getPhotoDetails(@Path("id") photoId: String): PhotoDto

}