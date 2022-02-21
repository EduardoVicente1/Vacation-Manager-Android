package com.example.vacation_manager_android

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotificationService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        var tittle = remoteMessage.notification?.title
        var description = remoteMessage.notification?.body
        var CHANNEL_ID = "HEADS_UP_NOTIFICATION"

        var channel = NotificationChannel(
            CHANNEL_ID,
            "Heads up Notification",
            NotificationManager.IMPORTANCE_HIGH
        )
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        var notification = Notification.Builder(this, CHANNEL_ID)
            .setContentTitle(tittle)
            .setContentText(description)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)

        NotificationManagerCompat.from(this).notify(1, notification.build())
        super.onMessageReceived(remoteMessage)
    }
}