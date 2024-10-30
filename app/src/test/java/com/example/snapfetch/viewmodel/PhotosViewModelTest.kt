@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.snapfetch.viewmodel

import com.example.snapfetch.domain.model.Photo
import com.example.snapfetch.domain.repository.PhotosRepository
import com.example.snapfetch.util.Response
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PhotosViewModelTest {

    private lateinit var repository: PhotosRepository
    private lateinit var viewModel: PhotosViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = PhotosViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getPhotos success updates uiState correctly`() = runTest {
        val mockPhotos = listOf(
            Photo(
                id = "1",
                author = "Author1",
                width = 800,
                height = 600,
                url = "",
                downloadUrl = ""
            )
        )
        coEvery {
            repository.getPhotos(
                page = 1,
                limit = PhotosViewModel.PHOTOS_LIMIT
            )
        } returns Response.Success(
            mockPhotos
        )
        viewModel.getPhotos()
        advanceUntilIdle()

        assertEquals(mockPhotos, viewModel.uiState.photos)
        assertFalse(viewModel.uiState.isLoading)
        assertFalse(viewModel.uiState.error)
        assertEquals(2, viewModel.currentPage)
    }

    @Test
    fun `getPhotos should handle error response and update state with error`() = runTest {
        val mockPhotos = listOf(
            Photo(
                id = "1",
                author = "Author1",
                width = 800,
                height = 600,
                url = "",
                downloadUrl = ""
            )
        )
        coEvery {
            repository.getPhotos(
                page = 1,
                limit = PhotosViewModel.PHOTOS_LIMIT
            )
        } returns Response.Error(data = mockPhotos, message = "Network error")

        viewModel.getPhotos()
        advanceUntilIdle()

        assertFalse(viewModel.uiState.isLoading)
        assertTrue(viewModel.uiState.error)
        assertTrue(viewModel.uiState.photos.isEmpty())
    }

    @Test
    fun `loadMore action should load more photos and increment currentPage`() = runTest {
        val mockPhotosPage1 = listOf(
            Photo(
                id = "1",
                author = "Author1",
                width = 800,
                height = 600,
                url = "",
                downloadUrl = ""
            )
        )
        val mockPhotosPage2 = listOf(
            Photo(
                id = "2",
                author = "Author2",
                width = 800,
                height = 600,
                url = "",
                downloadUrl = ""
            )
        )

        coEvery {
            repository.getPhotos(
                page = 0,
                limit = PhotosViewModel.PHOTOS_LIMIT
            )
        } returns Response.Success(mockPhotosPage1)
        coEvery {
            repository.getPhotos(
                page = 2,
                limit = PhotosViewModel.PHOTOS_LIMIT
            )
        } returns Response.Success(mockPhotosPage2)

        viewModel.getPhotos()
        viewModel.actions.loadMore()
        advanceUntilIdle()

        assertEquals(3, viewModel.currentPage)
    }

    @Test
    fun `getPhotos with empty response updates uiState correctly`() = runTest {
        coEvery { repository.getPhotos(any(), any()) } returns Response.Success(emptyList())

        viewModel.getPhotos()
        advanceUntilIdle()

        assertTrue(viewModel.uiState.photos.isEmpty())
        assertFalse(viewModel.uiState.isLoading)
        assertFalse(viewModel.uiState.error)
    }

    @Test
    fun `resetError clears the error state`() = runTest {
        val mockPhotos = listOf(
            Photo(
                id = "1",
                author = "Author1",
                width = 800,
                height = 600,
                url = "",
                downloadUrl = ""
            )
        )
        coEvery { repository.getPhotos(any(), any()) } returns Response.Error(
            data = mockPhotos,
            message = "Network error"
        )
        viewModel.getPhotos()
        advanceUntilIdle()

        assertTrue(viewModel.uiState.error)

        viewModel.actions.onClose()

        assertFalse(viewModel.uiState.error)
    }
}