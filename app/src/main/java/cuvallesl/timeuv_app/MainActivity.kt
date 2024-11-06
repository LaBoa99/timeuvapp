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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }// Ruta para la ventana de Login
        //Las ventanas se encuentran escritas en base a la estructura establecida de como se manejaria
        composable("Home") { HomeWindow(navController)} // Ruta para la ventana Home
        composable("InfoM") { InfoMWindow(navController) }// Ruta para la ventana de Materia que muestra la info
        composable("Mapa") { MapaWindow(navController)} // Ruta para la ventana Mapa
        //Rutas posibles dentro del apartado de Calendario
        composable("Calendario") { CalendarioWindow(navController) }// Ruta para la ventana de Calendario
        composable("Talleres") { TalleresWindow(navController) }// Ruta para la ventana de Talleres
        composable("Materias") { MateriasWindow(navController)} // Ruta para la ventana Materias
        //Rutas posibles dentro del apartado de Perfil
        composable("Perfil") { PerfilWindow(navController)} // Ruta para la pantalla de Perfil
        composable("Configuraciones") { ConfiguracionesWindow(navController) }// Ruta para la ventana de Configuraciones

    }
}
@Composable
fun LoginScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate("Home") }) {
            Text("Iniciar Sesion")
        }
    }
}

@Composable //Se añade esta nueva version, pero falta realizar acomodos
fun HomeWindow(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Primera opción con ícono
        Button(
            onClick = { navController.navigate("InfoM") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            )
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Inicio",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    "Ir a Inicio",
                    fontSize = 18.sp
                )
            }
        }

        // Segunda opción con ícono
        Button(
            onClick = { navController.navigate("Calendario") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50)
            )
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Calendar",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    "Calendar",
                    fontSize = 18.sp
                )
            }
        }

        // Tercera opción con ícono
        Button(
            onClick = { navController.navigate("Perfil") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF44336)
            )
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    "Perfil de Usuario",
                    fontSize = 18.sp
                )
            }
        }

        // Botón adicional para volver atrás
        TextButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Salir")
        }
    }
}
@Composable
fun InfoMWindow(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate("Mapa") }) {
            Text("Ver en el mapa")
        }
    }
}
@Composable
fun MapaWindow(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate("InfoM") }) { //La ruta a la que regresa es a InfoM con la intención de confirmar los datos en caso de haber llegado a la ubicacion
            Text("Regresar")
        }
    }
}
@Composable
fun CalendarioWindow(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { navController.navigate("Talleres") },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Ver Talleres")
            }
            Button(
                onClick = { navController.navigate("Materias") },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Ver Materias")
            }
        }
    }
}
@Composable
fun TalleresWindow(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate("Calendario") }) {
            Text("Registrar")
        }
    }
}
@Composable
fun MateriasWindow(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate("Calendario") }) {
            Text("Registrar")
        }
    }
}
@Composable
fun PerfilWindow(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate("Configuraciones") }) {
            Text("Configuraciones")
        }
    }
}
@Composable
fun ConfiguracionesWindow(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate("Perfil") }) {
            Text("Guardar")
        }
    }
}