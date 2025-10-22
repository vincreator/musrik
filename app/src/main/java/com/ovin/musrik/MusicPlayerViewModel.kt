package com.ovin.musrik

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MusicPlayerViewModel(private val context: Context) : ViewModel() {

    private var exoPlayer: ExoPlayer? = null
    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>> = _songs.asStateFlow()

    private val _currentSong = MutableStateFlow<Song?>(null)
    val currentSong: StateFlow<Song?> = _currentSong.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _currentPosition = MutableStateFlow(0L)
    val currentPosition: StateFlow<Long> = _currentPosition.asStateFlow()

    private val _duration = MutableStateFlow(0L)
    val duration: StateFlow<Long> = _duration.asStateFlow()

    private val musicRepository = MusicRepository(context.contentResolver)

    init {
        initializePlayer()
        loadSongs()
    }

    private fun initializePlayer() {
        exoPlayer = ExoPlayer.Builder(context).build().apply {
            addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    _isPlaying.value = playbackState == Player.STATE_READY && isPlaying
                }

                override fun onPositionDiscontinuity(
                    oldPosition: Player.PositionInfo,
                    newPosition: Player.PositionInfo,
                    reason: Int
                ) {
                    updateCurrentSong()
                }

                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    _isPlaying.value = isPlaying
                }
            })
        }
    }

    fun loadSongs() {
        viewModelScope.launch {
            _songs.value = musicRepository.getAllSongs()
        }
    }

    fun playSong(song: Song) {
        exoPlayer?.let { player ->
            val mediaItem = MediaItem.fromUri(song.data)
            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()
            _currentSong.value = song
            _duration.value = song.duration
        }
    }

    fun playPause() {
        exoPlayer?.let { player ->
            if (player.isPlaying) {
                player.pause()
            } else {
                player.play()
            }
        }
    }

    fun nextSong() {
        exoPlayer?.let { player ->
            if (player.hasNextMediaItem()) {
                player.seekToNext()
            }
        }
    }

    fun previousSong() {
        exoPlayer?.let { player ->
            if (player.hasPreviousMediaItem()) {
                player.seekToPrevious()
            }
        }
    }

    fun seekTo(position: Long) {
        exoPlayer?.seekTo(position)
    }

    private fun updateCurrentSong() {
        exoPlayer?.let { player ->
            val currentIndex = player.currentMediaItemIndex
            if (currentIndex >= 0 && currentIndex < _songs.value.size) {
                _currentSong.value = _songs.value[currentIndex]
            }
        }
    }

    fun updatePosition() {
        exoPlayer?.let { player ->
            _currentPosition.value = player.currentPosition
        }
    }

    override fun onCleared() {
        super.onCleared()
        exoPlayer?.release()
        exoPlayer = null
    }
}