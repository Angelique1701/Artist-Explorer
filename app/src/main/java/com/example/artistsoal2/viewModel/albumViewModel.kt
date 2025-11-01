package com.example.artistsoal2.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artistsoal2.model.Album
import com.example.artistsoal2.model.Track
import com.example.artistsoal2.repository.MusicRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlbumDetailViewModel(private val repository: MusicRepository) : ViewModel() {

    private val _album = MutableStateFlow<Album?>(null)
    val album: StateFlow<Album?> = _album

    private val _tracks = MutableStateFlow<List<Track>>(emptyList())
    val tracks: StateFlow<List<Track>> = _tracks

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchAlbumDetail(albumId: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val albumResponse = repository.getAlbumDetail(albumId)
                val trackResponse = repository.getTracks(albumId)

                _album.value = albumResponse.album?.firstOrNull()
                _tracks.value = trackResponse.track ?: emptyList()

            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Error loading album detail"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
