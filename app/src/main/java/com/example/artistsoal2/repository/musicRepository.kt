package com.example.artistsoal2.repository

import com.example.artistsoal2.model.AlbumResponse
import com.example.artistsoal2.model.TrackResponse
import com.example.artistsoal2.service.MusicService

class MusicRepository(private val api: MusicService) {

    suspend fun getArtist(name: String) = api.searchArtist(name)

    suspend fun getAlbums(name: String) = api.searchAlbum(name)

    suspend fun getAlbumDetail(id: String): AlbumResponse =
        api.getAlbumDetail(id)

    suspend fun getTracks(id: String): TrackResponse =
        api.getAlbumTracks(id)
}
