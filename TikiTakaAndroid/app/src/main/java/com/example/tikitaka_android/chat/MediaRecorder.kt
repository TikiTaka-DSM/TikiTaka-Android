package com.example.tikitaka_android.chat

import android.media.MediaRecorder
import android.util.Log
import java.io.IOException

object MediaRecorder {
    var recorder: MediaRecorder? = null

    fun startRecorder(filepath: String){
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setOutputFile(filepath)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)

            try{
                prepare()
            }catch (e: IOException){
                Log.e("MediaRecorder","prepare() failed")
            }
        }
    }

    fun stopRecorder(){
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }
}