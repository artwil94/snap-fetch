package com.example.snapfetch.domain.repository

import com.example.snapfetch.data.remote.PhotosApi
import com.example.snapfetch.domain.model.Photo
import com.example.snapfetch.domain.model.toEntity
import com.example.snapfetch.util.Response
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val api: PhotosApi
) : PhotosRepository {
    override suspend fun getPhotos(
    ): Response<List<Photo>> {
        return try {
            val result = api.getPhotos().map { it.toEntity() }.requireNoNulls()
            Response.Success(
                data = result
            )
        } catch (e: Exception) {
            Response.Error(message = "${e.message ?: "Unknown error"} - An issue occurred while fetching photos.")
        }
    }
}