package cuvallesl.timeuv_app
//Importacion de ventanas y procesos
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import cuvallesl.timeuv_app.navigation.NavManager
//dependencias
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Surface
import cuvallesl.timeuv_app.fcm.genAndSaveToken
import cuvallesl.timeuv_app.fcm.getTokenFromPreferences



class MainActivity : ComponentActivity() {
    companion object {
        const val NOTIFICATION_CHANNEL_ID = "notification_fcm"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Generamos un token de prueba eliminar! -->
        genAndSaveToken(this);
        println(getTokenFromPreferences(this))
        createNotificationChannel()

        setContent {
            // Usar un NavController para la navegación
            val navController = rememberNavController()
            NavManager(navController = navController) // Configurar el NavManager
        }
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Notificaciones de FCM",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificaionManager = getSystemService(NotificationManager::class.java)
            notificaionManager.createNotificationChannel(channel)
        }
    }
}

