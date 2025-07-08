package com.example.lecturaparaprimaria.utils

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

class SoundPlayer(private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null

    fun playSound(soundResId: Int) {
        try {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(context, soundResId).apply {
                setVolume(0.7f, 0.7f)
                setOnCompletionListener { it.release() }
                start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}

@Composable
fun rememberSoundPlayer(): SoundPlayer {
    val context = LocalContext.current
    return androidx.compose.runtime.remember { SoundPlayer(context) }
}