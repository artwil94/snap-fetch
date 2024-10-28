package com.example.snapfetch.domain.repository

import com.example.snapfetch.data.remote.PhotosApi
import com.example.snapfetch.domain.model.Photo
import com.example.snapfetch.domain.model.toEntity
import com.example.snapfetch.util.Response
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(
    private val photosApi: PhotosApi
) : PhotosRepository {
    override suspend fun getPhotos(
        page: Int,
        limit: Int
    ): Response<List<Photo>> {
        return try {
            val result =
                photosApi.getPhotos(page,limit).map { it.toEntity() }.requireNoNulls()
            Response.Success(
                data = result
            )
        } catch (e: Exception) {
            Response.Error(message = "${e.message ?: "Unknown error"} - An issue occurred while fetching photos.")
        }
    }
}