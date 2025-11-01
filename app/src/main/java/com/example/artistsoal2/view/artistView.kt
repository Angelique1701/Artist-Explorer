package com.example.artistsoal2.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.artistsoal2.viewModel.ArtistViewModel

@Composable
fun ArtistScreen(viewModel: ArtistViewModel, navController: NavController) {
    val artist by viewModel.artist.collectAsState()
    val albums by viewModel.albums.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    when {
        isLoading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color(0xFFd79921))
        }

        error != null -> Text(text = " ${error}", color = Color.Red)

        artist != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Foto artis
                Image(
                    painter = rememberAsyncImagePainter(artist?.strArtistThumb),
                    contentDescription = "Artist Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(Modifier.height(8.dp))
                Text(
                    artist?.strArtist ?: "",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = Color(0xFFebdbb2),
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    artist?.strGenre ?: "",
                    color = Color(0xFFb8bb26)
                )

                Spacer(Modifier.height(16.dp))
                Text(
                    text = "Albums",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color(0xFFfabd2f),
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(Modifier.height(8.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(albums) { album ->
                        Card(
                            colors = CardDefaults.outlinedCardColors(containerColor = Color(0xFF3c3836)),
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate("albumDetail/${album.idAlbum}")
                                },
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(album.strAlbumThumb),
                                    contentDescription = album.strAlbum,
                                    modifier = Modifier
                                        .height(100.dp)
                                        .fillMaxWidth(),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = album.strAlbum ?: "",
                                    color = Color(0xFFebdbb2),
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = album.strGenre ?: "",
                                    color = Color(0xFFb8bb26),
                                    fontSize = MaterialTheme.typography.bodySmall.fontSize
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun ArtistScreenPreview() {
    // Dummy preview without real data
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Artist Name",
            style = MaterialTheme.typography.headlineSmall.copy(
                color = Color(0xFFebdbb2),
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            "Genre",
            color = Color(0xFFb8bb26)
        )

        Spacer(Modifier.height(16.dp))
        Text(
            text = "Albums",
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color(0xFFfabd2f),
                fontWeight = FontWeight.Bold
            )
        )
    }
}
