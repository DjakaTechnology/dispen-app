package com.lutungkamarsung.dispen

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.FirebaseMessagingService
import androidx.core.app.NotificationManagerCompat
import android.content.Context.NOTIFICATION_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.app.NotificationManager
import android.R
import android.media.RingtoneManager
import android.app.PendingIntent
import android.app.NotificationChannel
import android.os.Build








class FirebaseMC : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Log.d("FIREBASE", remoteMessage!!.notification!!.body)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val manager = NotificationManagerCompat.from(applicationContext)
        val channelId = "00011"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "NotificationChannel1",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this)
            .setContentTitle(remoteMessage.notification!!.title)
            .setContentText(remoteMessage.notification!!.body)
            .setSmallIcon(R.drawable.ic_menu_edit)
            .setAutoCancel(true)
            .setChannelId(channelId)
            .setSound(defaultSoundUri)
            .build()

        manager.notify(/*notification id*/0, notification)
    }

    companion object {

        val TAG = "MsgFirebaseServ"
    }
}