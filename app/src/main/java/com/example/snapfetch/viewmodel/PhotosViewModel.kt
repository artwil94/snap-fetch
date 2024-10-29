package com.example.snapfetch.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snapfetch.domain.model.Photo
import com.example.snapfetch.domain.repository.PhotosRepository
import com.example.snapfetch.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val repository: PhotosRepository,
) : ViewModel() {

    var uiState by mutableStateOf(PhotosUIState())
        private set

    val actions = PhotosActions(
        loadMore = {
            getPhotos()
        },
        onTryAgain = { getPhotos() },
        onClose = { resetError() }
    )
    private var currentPage = 1

    init {
        getPhotos()
    }

    internal fun getPhotos() {
        viewModelScope.launch {
            uiState = uiState.copy(
                error = false,
                isLoading = true
            )
            val result = repository.getPhotos(page = currentPage, limit = PHOTOS_LIMIT)
            when (result) {
                is Response.Success -> {
                    val newPhotos = result.data ?: emptyList()
                    uiState = uiState.copy(
                        photos = uiState.photos + newPhotos,
                        isLoading = false,
                        error = false
                    )
                    currentPage++
                }

                is Response.Error -> {
                    uiState = uiState.copy(
                        photos = listOf(),
                        error = true,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun resetError() {
        uiState = uiState.copy(
            error = false
        )
    }

    companion object {
        const val PHOTOS_LIMIT = 20
    }
}

data class PhotosActions(
    val loadMore: () -> Unit,
    val onTryAgain: () -> Unit,
    val onClose: () -> Unit
)

data class PhotosUIState(
    val isLoading: Boolean = true,
    val error: Boolean = false,
    var photos: List<Photo> = listOf(),
)