package com.example.artistsoal2.model

data class ArtistResponse(
    val artists: List<Artist>?
)

data class Artist(
    val idArtist: String,
    val strArtist: String?,
    val strGenre: String?,
    val strArtistThumb: String?,
    val strArtistBanner: String?,
    val strBiographyEN: String?
)
