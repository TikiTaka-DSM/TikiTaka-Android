package com.example.tikitaka_android.chat

import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import com.example.tikitaka_android.network.TikiTakaConnect

object MediaPlayer{
    var player: MediaPlayer? = null

    fun initPlayer(voiceUri: Uri){
        player = MediaPlayer().apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(TikiTakaConnect.s3 + voiceUri)
            prepare()
            start()
        }
    }

    fun pausePlayer(){
        player?.pause()
    }

    fun restartPlayer(){
        player?.start()
    }

    fun stopPlayer(){
        player?.stop()
        player?.release()
        player = null
    }
}