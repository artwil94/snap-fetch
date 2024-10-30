package com.example.snapfetch.viewmodel

import com.example.snapfetch.domain.model.Photo
import com.example.snapfetch.domain.repository.PhotosRepository
import com.example.snapfetch.util.Response
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PhotoDetailsViewModelTest {

    private lateinit var viewModel: PhotoDetailsViewModel
    private val repository: PhotosRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = PhotoDetailsViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getPhotoDetails success updates uiState correctly`() = runTest {
        val mockPhoto = Photo(
            id = "1",
            author = "Author1",
            width = 800,
            height = 600,
            url = "https://example.com",
            downloadUrl = "https://example.com/download"
        )

        coEvery { repository.getPhotoDetails(photoId = "1") } returns Response.Success(mockPhoto)

        viewModel.actions.start("1")
        advanceUntilIdle()

        assertEquals(mockPhoto, viewModel.uiState.photo)
        assertFalse(viewModel.uiState.isLoading)
        assertFalse(viewModel.uiState.error)
    }

    @Test
    fun `getPhotoDetails with error response sets error state`() = runTest {
        val mockPhoto = Photo(
            id = "1",
            author = "Author1",
            width = 800,
            height = 600,
            url = "https://example.com",
            downloadUrl = "https://example.com/download"
        )
        coEvery { repository.getPhotoDetails(photoId = "1") } returns Response.Error(
            data = mockPhoto,
            message = "Network error"
        )

        viewModel.actions.start("1")
        advanceUntilIdle()

        assertNull(viewModel.uiState.photo)
        assertFalse(viewModel.uiState.isLoading)
        assertTrue(viewModel.uiState.error)
    }

    @Test
    fun `resetError clears the error state`() = runTest {
        val mockPhotos =
            Photo(
                id = "1",
                author = "Author1",
                width = 800,
                height = 600,
                url = "",
                downloadUrl = ""
            )
        coEvery { repository.getPhotoDetails(photoId = "1") } returns Response.Error(
            data = mockPhotos,
            message = "Network error"
        )
        viewModel.actions.start("1")
        advanceUntilIdle()

        assertTrue(viewModel.uiState.error)

        viewModel.actions.onClose()
        advanceUntilIdle()

        assertFalse(viewModel.uiState.error)
    }
}
