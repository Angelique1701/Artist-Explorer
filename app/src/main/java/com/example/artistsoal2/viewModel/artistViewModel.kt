package com.example.artistsoal2.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artistsoal2.model.Artist
import com.example.artistsoal2.model.Album
import com.example.artistsoal2.repository.MusicRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArtistViewModel(private val repo: MusicRepository) : ViewModel() {

    private val _artist = MutableStateFlow<Artist?>(null)
    val artist: StateFlow<Artist?> = _artist

    private val _albums = MutableStateFlow<List<Album>>(emptyList())
    val albums: StateFlow<List<Album>> = _albums

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchArtistData(name: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val artistRes = repo.getArtist(name)
                val albumRes = repo.getAlbums(name)
                _artist.value = artistRes.artists?.firstOrNull()
                _albums.value = albumRes.album ?: emptyList()
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
