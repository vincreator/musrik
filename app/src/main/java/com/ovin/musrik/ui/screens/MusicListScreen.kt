package com.ovin.musrik.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ovin.musrik.MusicPlayerViewModel
import com.ovin.musrik.ui.components.MusicItem
import com.ovin.musrik.ui.components.PlayerControls
import com.ovin.musrik.ui.components.SeekBar
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicListScreen(
    viewModel: MusicPlayerViewModel = viewModel(),
    onNavigateToPlayer: () -> Unit
) {
    val songs by viewModel.songs.collectAsState()
    val currentSong by viewModel.currentSong.collectAsState()
    val isPlaying by viewModel.isPlaying.collectAsState()
    val currentPosition by viewModel.currentPosition.collectAsState()
    val duration by viewModel.duration.collectAsState()

    // Update position periodically
    LaunchedEffect(isPlaying) {
        while (isPlaying) {
            delay(1000)
            viewModel.updatePosition()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Musrik Music Player") }
            )
        },
        bottomBar = {
            if (currentSong != null) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        // Mini player info
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = currentSong?.title ?: "",
                                style = MaterialTheme.typography.titleSmall,
                                modifier = Modifier.weight(1f),
                                maxLines = 1
                            )
                            Text(
                                text = currentSong?.artist ?: "",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        SeekBar(
                            currentPosition = currentPosition,
                            duration = duration,
                            onSeek = viewModel::seekTo
                        )

                        PlayerControls(
                            isPlaying = isPlaying,
                            onPlayPause = viewModel::playPause,
                            onNext = viewModel::nextSong,
                            onPrevious = viewModel::previousSong
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        if (songs.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No music found on device",
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(songs) { song ->
                    MusicItem(
                        song = song,
                        onClick = {
                            viewModel.playSong(song)
                            onNavigateToPlayer()
                        }
                    )
                }
            }
        }
    }
}