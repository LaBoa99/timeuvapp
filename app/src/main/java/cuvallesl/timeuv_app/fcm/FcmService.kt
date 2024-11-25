package cuvallesl.timeuv_app.fcm

import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import cuvallesl.timeuv_app.MainActivity.Companion.NOTIFICATION_CHANNEL_ID
import cuvallesl.timeuv_app.R


class FcmService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        showNotification(message)
    }

    private fun showNotification(message: RemoteMessage){
        val notificationManager = getSystemService(NotificationManager::class.java)
        val notification = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle(message.notification?.title)
            .setContentText(message.notification?.body)
            .setSmallIcon(R.drawable.notification_logo)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1, notification)

    }
}