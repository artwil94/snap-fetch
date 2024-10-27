package com.example.snapfetch.domain.repository

import com.example.snapfetch.domain.model.Photo
import com.example.snapfetch.util.Response

interface PhotosRepository {

    suspend fun getPhotos(): Response<List<Photo>>
}