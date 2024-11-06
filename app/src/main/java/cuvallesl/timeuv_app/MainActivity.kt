package cuvallesl.timeuv_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainApp()

        }
    }
}
@Composable
fun MainApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "first_window") {
        composable("first_window") { FirstWindow(navController) }
        composable("second_window/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")
            SecondWindow(navController, name)
        }
    }
}

@Composable
fun FirstWindow(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Esta es la primera ventana")
            Button(onClick = { navController.navigate("second_window/John") }) {
                Text("Ir a la segunda ventana")
            }
        }
    }
}

@Composable
fun SecondWindow(navController: NavController, name: String?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Esta es la segunda ventana")
            name?.let { Text("Hola, $it!") }
            Button(onClick = { navController.navigate("first_window") }) {
                Text("Volver a la primera ventana")
            }
        }
    }
}

