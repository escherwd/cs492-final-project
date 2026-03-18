package edu.oregonstate.cs492finalproject.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import edu.oregonstate.cs492finalproject.data.model.AlbumDetail
import edu.oregonstate.cs492finalproject.data.repository.AlbumRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DetailUiState(
    val isLoading: Boolean = false,
    val albumDetail: AlbumDetail? = null,
    val error: String? = null
)

class DetailViewModel(
    private val repository: AlbumRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState(isLoading = false))
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun loadAlbumDetail(albumId: String) {
        viewModelScope.launch {
            _uiState.value = DetailUiState(isLoading = true)

            try {
                val detail = repository.getAlbumDetail(albumId)
                _uiState.value = DetailUiState(
                    isLoading = false,
                    albumDetail = detail,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = DetailUiState(
                    isLoading = false,
                    albumDetail = null,
                    error = e.message ?: "Failed to load album details."
                )
            }
        }
    }

    companion object {
        fun provideFactory(repository: AlbumRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return DetailViewModel(repository) as T
                }
            }
    }
}