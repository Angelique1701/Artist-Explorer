package com.example.artistsoal2.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.artistsoal2.viewModel.AlbumDetailViewModel

@Composable
fun AlbumDetailScreen(viewModel: AlbumDetailViewModel, albumId: String) {
    val album by viewModel.album.collectAsState()
    val tracks by viewModel.tracks.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(albumId) {
        viewModel.fetchAlbumDetail(albumId)
    }

    when {
        isLoading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color(0xFFd79921))
        }

        error != null -> Text("⚠️ ${error}", color = Color.Red)

        album != null -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    Image(
                        painter = rememberAsyncImagePainter(album?.strAlbumThumb),
                        contentDescription = "Album Cover",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        album?.strAlbum ?: "",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = Color(0xFFebdbb2),
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text("Genre: ${album?.strGenre ?: "-"}", color = Color(0xFFb8bb26))
                    Text("Released: ${album?.intYearReleased ?: "-"}", color = Color(0xFFd5c4a1))
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = album?.strDescriptionEN ?: "",
                        color = Color(0xFFebdbb2)
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Tracks",
                        color = Color(0xFFfabd2f),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(Modifier.height(8.dp))
                }

                items(tracks) { track ->
                    Text(
                        text = "${track.intTrackNumber}. ${track.strTrack}",
                        color = Color(0xFFebdbb2),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}
