package com.tutorialsbuzz.androidcustomnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener({
            createCustomNotification()
        })

    }

    private fun createCustomNotification() {

        val notificationID: Int = 100
        // Create an explicit intent for an Activity in your app
        val intent = Intent(this, NotifyActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        intent.data = Uri.parse("https://www.tutorialsbuzz.com/")

        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val channelId = "News"
        val channelName = "News"

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notifications_active)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(500, 500, 500))

        val smallContent = RemoteViews(getPackageName(), R.layout.small_layout_notification)
        val bigContent = RemoteViews(getPackageName(), R.layout.large_notification_layout)

        bigContent.setTextViewText(R.id.notification_title, "Notification Custom Text")
        smallContent.setTextViewText(R.id.notification_title, "Notification Custom Text")

        bigContent.setImageViewResource(R.id.notification_image, R.mipmap.ic_launcher)
        smallContent.setImageViewResource(R.id.notification_image, R.mipmap.ic_launcher)

        builder.setContent(smallContent)
        builder.setCustomBigContentView(bigContent)

        with(NotificationManagerCompat.from(this)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_HIGH
                )
                createNotificationChannel(channel)
                notify(notificationID, builder.build())
            }
        }
    }

}