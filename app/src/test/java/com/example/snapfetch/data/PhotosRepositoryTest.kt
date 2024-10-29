package com.example.snapfetch.data

import com.example.snapfetch.data.remote.PhotosApi
import com.example.snapfetch.data.remote.dtos.PhotoDto
import com.example.snapfetch.domain.repository.PhotosRepository
import com.example.snapfetch.domain.repository.PhotosRepositoryImpl
import com.example.snapfetch.util.Response
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PhotosRepositoryImplTest {

    private lateinit var repository: PhotosRepository
    private val photosApi: PhotosApi = mockk()

    @Before
    fun setup() {
        repository = PhotosRepositoryImpl(photosApi)
    }

    @Test
    fun `getPhotos  returns expected photos on success`() = runTest {
        val mockPhotos = listOf(
            PhotoDto(
                id = "1",
                author = "Author1",
                width = 500,
                height = 500,
                url = "",
                downloadUrl = ""
            ),
            PhotoDto(
                id = "2",
                author = "Author2",
                width = 600,
                height = 600,
                url = "",
                downloadUrl = ""
            )
        )
        coEvery { photosApi.getPhotos(page = 1, limit = 20) } returns mockPhotos

        val result = repository.getPhotos(page = 1, limit = 20)
        val photos = result.data!!

        assertTrue(result is Response.Success)
        assertEquals(2, photos.size)
        assertEquals(mockPhotos.first().author, photos.first().author)
        assertEquals(mockPhotos[1].author, photos[1].author)
        assertEquals(mockPhotos[1].width, photos[1].width)
        assertEquals(mockPhotos[1].height, photos[1].height)
        assertEquals(mockPhotos[1].downloadUrl, photos[1].downloadUrl)
        assertEquals(mockPhotos[1].url, photos[1].url)

        coVerify { photosApi.getPhotos(page = 1, limit = 20) }
    }

    @Test
    fun `getPhotos returns error when API throws exception`() = runTest {

        coEvery { photosApi.getPhotos(page = 1, limit = 20) } throws Exception("Network error")

        val result = repository.getPhotos(page = 1, limit = 20)

        assert(result is Response.Error)

        coVerify { photosApi.getPhotos(page = 1, limit = 20) }
    }
    @Test
    fun `getPhotos  returns empty list on empty API response`() = runTest {
        val mockPhotos = emptyList<PhotoDto>()
        coEvery { photosApi.getPhotos(page = 1, limit = 20) } returns mockPhotos

        val result = repository.getPhotos(page = 1, limit = 20)
        val photos = result.data!!

        assertTrue(result is Response.Success)
        assertEquals(0, photos.size)

        coVerify { photosApi.getPhotos(page = 1, limit = 20) }
    }
    @Test
    fun `getPhotoDetails returns success when API returns data`() = runTest {
        val mockPhoto = PhotoDto(
            id = "1",
            author = "Author1",
            width = 500,
            height = 500,
            url = "",
            downloadUrl = ""
        )
        coEvery { photosApi.getPhotoDetails(photoId = "1") } returns mockPhoto

        val result = repository.getPhotoDetails(photoId = "1")

        assert(result is Response.Success)
        assertEquals((result as Response.Success).data, mockPhoto)

        coVerify { photosApi.getPhotoDetails(photoId = "1") }
    }

    @Test
    fun `getPhotoDetails returns error when API throws exception`() = runTest {
        coEvery { photosApi.getPhotoDetails(photoId = "1") } throws Exception("Network error")

        val result = repository.getPhotoDetails(photoId = "1")

        assert(result is Response.Error)
        assertEquals(
            (result as Response.Error).message,
            "Network error - An issue occurred while fetching photo details."
        )

        coVerify { photosApi.getPhotoDetails(photoId = "1") }
    }
}
