package com.example.android_2_kurs.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.android_2_kurs.R
import com.example.android_2_kurs.entity.SongRepository
import com.example.android_2_kurs.layout.MainActivity
import com.example.android_2_kurs.service.MusicService

class NotificationController(private val context: Context){

    private val CHANNEL_ID = "xokken_music"
    private val notificationId = 1
    private var notificationManager: NotificationManager? = null
    private var notificationBuilder: NotificationCompat.Builder
    var currentSongId: Int? = null

    init{
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = notificationManager?.getNotificationChannel(CHANNEL_ID) ?:  NotificationChannel(
                CHANNEL_ID,
                name,
                importance
            ).apply {
                description = descriptionText
            }
            notificationManager?.createNotificationChannel(channel)
        }

        val previousIntent = Intent(context, MusicService::class.java).apply {
            action = "PREVIOUS"
        }
        val resumeIntent = Intent(context, MusicService::class.java).apply {
            action = "RESUME"
        }
        val nextIntent = Intent(context, MusicService::class.java).apply {
            action = "NEXT"
        }
        val intentt = Intent(context, MainActivity::class.java).apply {
            this.setAction(Intent.ACTION_MAIN);
            this.addCategory(Intent.CATEGORY_LAUNCHER);
            this.putExtra("key", currentSongId.toString())
        }
        val previousPendingIntent = PendingIntent.getService(context, 0, previousIntent, 0)
        val resumePendingIntent = PendingIntent.getService(context, 1, resumeIntent, 0)
        val nextPendingIntent = PendingIntent.getService(context, 2, nextIntent, 0)
        val pIntent = PendingIntent.getActivity(context, 3, intentt, PendingIntent.FLAG_UPDATE_CURRENT)
        notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_play_arrow_black_18dp)
            .addAction(R.drawable.ic_skip_previous_24px, "Prev", previousPendingIntent)
            .addAction(R.drawable.ic_play_arrow_black_18dp, "Pause", resumePendingIntent)
            .addAction(R.drawable.ic_skip_next_24px, "Next", nextPendingIntent)
            .setContentIntent(pIntent)
            .setNotificationSilent()

    }



    fun build(id: Int){
        val intentt = Intent(context, MainActivity::class.java).apply {
            this.setAction(Intent.ACTION_MAIN);
            this.addCategory(Intent.CATEGORY_LAUNCHER);
            this.putExtra("key", id.toString())
        }
        currentSongId = id
        val song = SongRepository.getRepository()[id]
        val cover = BitmapFactory.decodeResource(context.resources, song.cover)
        val pIntent = PendingIntent.getActivity(context, 3, intentt, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = notificationBuilder
            .setContentTitle(song.name)
            .setContentText(song.author)
            .setContentIntent(pIntent)
            .setLargeIcon(cover)
        val notification: Notification = builder.build()
        notificationManager?.notify(notificationId, notification)
    }



}