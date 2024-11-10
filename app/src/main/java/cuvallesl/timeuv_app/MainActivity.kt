package cuvallesl.timeuv_app
//Importacion de ventanas y procesos
import cuvallesl.timeuv_app.navigation.NavManager
//dependencias
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Surface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Usar un NavController para la navegación
            val navController = rememberNavController()
            NavManager(navController = navController) // Configurar el NavManager
        }
    }
}