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

    fun getPhotos() {
        viewModelScope.launch {
            uiState = uiState.copy(
                isLoading = true
            )
            val result = repository.getPhotos()
            when (result) {
                is Response.Success -> {
                    result.data.let {
                        uiState = uiState.copy(
                            photos = result.data!!,
                            isLoading = false,
                            error = null
                        )
                    }
                }

                is Response.Error -> {
                    uiState = uiState.copy(
                        photos = listOf(),
                        error = "Photos API response error",
                        isLoading = false
                    )
                }
            }
        }
    }
}

data class PhotosUIState(
    val isLoading: Boolean = true,
    val error: String? = null,
    var photos: List<Photo> = listOf(),
)