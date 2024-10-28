package com.example.snapfetch.data.remote

import com.example.snapfetch.data.remote.dtos.PhotoDto
import retrofit2.http.GET

interface PhotosApi {

    @GET("v2/list")
    suspend fun getPhotos(): List<PhotoDto>

//    @GET()
//    suspend fun getPhotoDetails(photoId: String): PhotoDetailsDto

}