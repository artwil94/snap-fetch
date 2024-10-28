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
class PhotoDetailsViewModel @Inject constructor(
    private val repository: PhotosRepository,
) : ViewModel() {

    var uiState by mutableStateOf(PhotoDetailsUIState())
        private set

    val actions = PhotoDetailsActions(
        start = ::getPhotoDetails
    )

    private fun getPhotoDetails(photoId: String) {
        viewModelScope.launch {
            uiState = uiState.copy(
                isLoading = true
            )
            val result = repository.getPhotoDetails(photoId = photoId)
            when (result) {
                is Response.Success -> {
                    uiState = uiState.copy(
                        photo = result.data,
                        isLoading = false,
                        error = null
                    )
                }

                is Response.Error -> {
                    uiState = uiState.copy(
                        photo = null,
                        error = "Photos API response error",
                        isLoading = false
                    )
                }
            }
        }
    }
}

data class PhotoDetailsActions(
    val start: (photoId: String) -> Unit = {}
)

data class PhotoDetailsUIState(
    val isLoading: Boolean = true,
    val error: String? = null,
    var photo: Photo? = null
)