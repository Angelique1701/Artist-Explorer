package com.example.artistsoal2.service

import com.example.artistsoal2.model.ArtistResponse
import com.example.artistsoal2.model.AlbumResponse
import com.example.artistsoal2.model.TrackResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicService {

    companion object {
        const val API_KEY = "123"
    }

    // Cari artis
    @GET("api/v1/json/$API_KEY/search.php")
    suspend fun searchArtist(@Query("s") artistName: String): ArtistResponse

    // Cari album
    @GET("api/v1/json/$API_KEY/searchalbum.php")
    suspend fun searchAlbum(@Query("s") artistName: String): AlbumResponse

    // Detail album
    @GET("api/v1/json/$API_KEY/album.php")
    suspend fun getAlbumDetail(@Query("m") albumId: String): AlbumResponse

    // Track album
    @GET("api/v1/json/$API_KEY/track.php")
    suspend fun getAlbumTracks(@Query("m") albumId: String): TrackResponse
}
