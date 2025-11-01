package com.example.artistsoal2.model

data class TrackResponse(
    val track: List<Track>?
)

data class Track(
    val idTrack: String,
    val strTrack: String?,
    val intTrackNumber: String?
)
