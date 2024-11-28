package cuvallesl.timeuv_app

// Importaciones necesarias
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.media.session.MediaSession.Token
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import cuvallesl.timeuv_app.fcm.genAndSaveToken
import cuvallesl.timeuv_app.fcm.getTokenFromPreferences
import cuvallesl.timeuv_app.navigation.NavManager
import cuvallesl.timeuv_app.network.token.TokenStore

class MainActivity : ComponentActivity() {
    companion object {
        const val NOTIFICATION_CHANNEL_ID = "notification_fcm"
    }

    // Registro de permisos dentro de la clase
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (y tu app) pueden enviar notificaciones
        } else {
            // Notificar al usuario que el permiso es necesario
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        TokenStore.initialize(this)
        genAndSaveToken(this);
        println(getTokenFromPreferences(this))

        // Crear un canal de notificación si es necesario
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Notificaciones de FCM",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Canal predeterminado para notificaciones"
            }

            val notificationManager = ContextCompat.getSystemService(
                this, NotificationManager::class.java
            )
            notificationManager?.createNotificationChannel(channel)
        }
        requestPermissions()
        // Configurar el contenido de la actividad
        setContent {
            MaterialTheme {
                Surface {
                    val navController = rememberNavController()
                    NavManager(navController = navController)
                }
            }
        }
    }

    private fun requestPermissions() {
        val permissions = arrayOf(
            android.Manifest.permission.POST_NOTIFICATIONS,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            // ... otros permisos que necesites ...
        )

        permissions.forEach { permission ->
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(permission)
            }
        }
    }

}
