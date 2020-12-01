package com.example.android_2_kurs.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.example.android_2_kurs.entity.SongRepository
import com.example.android_2_kurs.notifications.NotificationController

class MusicService : Service() {

    private val CHANNEL_ID = "xokken_music"
    private val notificationId = 1
    lateinit var mediaPlayer: MediaPlayer
    var currentSong = 0
    var songList = SongRepository.getRepository()
    private lateinit var musicBinder: MusicBinder
    private lateinit var notificationController: NotificationController


    inner class MusicBinder : Binder() {
        fun getService(): MusicService = this@MusicService

    }

    override fun onCreate() {
        super.onCreate()
        init()
        initNotification()
    }

    override fun onBind(intent: Intent): IBinder = musicBinder

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            "PREVIOUS" -> {
                playPrevSong()
                notificationController.currentSongId = startId
            }
            "RESUME" -> {
                playSong()
                notificationController.currentSongId = startId
            }
            "NEXT" -> {
                playNextSong()
                notificationController.currentSongId = startId
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    private fun init() {
        mediaPlayer = MediaPlayer()
        musicBinder = MusicBinder()
    }

    private fun initNotification(){
        notificationController = NotificationController(this).apply {
            build(2)
        }

    }

    fun playPrevSong() {
        currentSong.let {
            currentSong = if (it == 0) {
                songList.size - 1
            } else {
                it - 1
            }
            setSong(currentSong)
            playSong()
        }
    }

    fun playNextSong() {
        currentSong.let {
            currentSong = if (it == songList.size -1) {
                0
            } else {
                it + 1
            }
            setSong(currentSong)
            playSong()
        }
    }


    fun playSong() {
        if (mediaPlayer.isPlaying){
            mediaPlayer.pause()
        }
        else {
            mediaPlayer.start()
        }
    }

    fun setSong(id: Int) {
        if (mediaPlayer.isPlaying) mediaPlayer.stop()
        mediaPlayer = MediaPlayer.create(applicationContext, songList[id].file)
        currentSong = id
        mediaPlayer.run {
            setOnCompletionListener {
                stop()
            }
        }
        notificationController.build(id)
        notificationController.currentSongId = id
    }

}