package com.ovin.musrik

data class Song(
    val id: Long,
    val title: String,
    val artist: String,
    val album: String,
    val duration: Long,
    val data: String, // file path
    val albumArtUri: String? = null
)