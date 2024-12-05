package cuvallesl.timeuv_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cuvallesl.timeuv_app.views.*
//Se importan las ventanas para el manejo de ventanas
@Composable
fun NavManager(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "Login/{email}/{materia}") {
        composable("Login/{email}/{materia}") {backStackEntry ->
            val email = backStackEntry.arguments?.getString("email")?:"Unknown"
            val materia = backStackEntry.arguments?.getString("materia")?:"1"
            LoginView(email = email,materia = materia,navController = navController) //Se establecen los parametros que va a recibir LoginView ya sea cuando se incializa o cuando se vuelva a acceder a la ventana
        }
        composable("NewAccount/{email}/{materia}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email")?:"Prueba"
            val materia = backStackEntry.arguments?.getString("materia")?:"1"
            NewAccView(email = email,materia = materia,navController = navController) // Llama a NewAccView
        }
        //Las ventanas se encuentran escritas en base a la estructura establecida de como se manejaria
        composable("Home/{email}/{materia}") {backStackEntry ->
            val email = backStackEntry.arguments?.getString("email")?:"Unknown"
            val materia = backStackEntry.arguments?.getString("materia")?:"1"
            HomeView(email = email,materia = materia, navController = navController) // Llama a HomeView
        }
        composable("InfoM/{email}/{materia}") {backStackEntry ->
            val email = backStackEntry.arguments?.getString("email")?:"Unknown"
            val materia = backStackEntry.arguments?.getString("materia")?:"1"
            InfoMView(email = email ,materia = materia,navController = navController) // Llama a InfoMView
        }
        composable("Mapa/{email}/{materia}") {backStackEntry ->
            val email = backStackEntry.arguments?.getString("email")?:"Unknown"
            val materia = backStackEntry.arguments?.getString("materia")?:"1"
            MapaView(email = email,materia = materia ,navController = navController)// Llama a MapaView
        }
        //Rutas posibles dentro del apartado de Calendario
        composable("Calendario/{email}/{materia}") {backStackEntry ->
            val email = backStackEntry.arguments?.getString("email")?:"Unknown"
            val materia = backStackEntry.arguments?.getString("materia")?:"1"
            CalendarView(email = email,materia = materia ,navController = navController) // Llama a CalendarView
        }
        composable("Talleres/{email}/{materia}") {backStackEntry ->
            val email = backStackEntry.arguments?.getString("email")?:"Unknown"
            val materia = backStackEntry.arguments?.getString("materia")?:"1"
            TalleresView(email = email,materia = materia ,navController = navController) // Llama a TalleresView
        }
        composable("Materias/{email}/{materia}") {backStackEntry ->
            val email = backStackEntry.arguments?.getString("email")?:"Unknown"
            val materia = backStackEntry.arguments?.getString("materia")?:"1"
            MateriasView(email = email,materia = materia ,navController = navController) // Llama a MateriasView
        }
        //Rutas posibles dentro del apartado de Perfil
        composable("Profile/{email}/{materia}"){backStackEntry ->
            val email = backStackEntry.arguments?.getString("email")?:"Unknown"
            val materia = backStackEntry.arguments?.getString("materia")?:"1"
            ProfileView(email = email,materia = materia ,navController = navController)// Llama a ProfileView
        }
        composable("Configuraciones/{email}/{materia}") {backStackEntry ->
            val email = backStackEntry.arguments?.getString("email")?:"Unknown"
            val materia = backStackEntry.arguments?.getString("materia")?:"1"
            SettingsView(email = email,materia = materia ,navController = navController) // Llama a SettingsView
        }
    }

}