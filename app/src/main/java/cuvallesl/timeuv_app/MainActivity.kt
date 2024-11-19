package cuvallesl.timeuv_app

// Importaciones necesarias
import android.app.NotificationChannel
import android.app.NotificationManager
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
}
