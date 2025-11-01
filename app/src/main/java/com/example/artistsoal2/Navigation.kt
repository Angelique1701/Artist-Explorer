package com.example.artistsoal2

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.artistsoal2.view.ArtistScreen
import com.example.artistsoal2.view.AlbumDetailScreen
import com.example.artistsoal2.viewModel.AlbumDetailViewModel
import com.example.artistsoal2.viewModel.ArtistViewModel

@Composable
fun AppNavigation(artistViewModel: ArtistViewModel, albumViewModel: AlbumDetailViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "artistView"
    ) {
        composable("artistView") {
            ArtistScreen(viewModel = artistViewModel, navController = navController)
        }

        composable(
            route = "albumDetail/{albumId}",
            arguments = listOf(navArgument("albumId") { type = NavType.StringType })
        ) { backStackEntry ->
            val albumId = backStackEntry.arguments?.getString("albumId") ?: ""
            AlbumDetailScreen(viewModel = albumViewModel, albumId = albumId)
        }
    }
}
