package cuvallesl.timeuv_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cuvallesl.timeuv_app.views.*
//Se importan las ventanas para el manejo de ventanas
@Composable
fun NavManager(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "Login") {
        composable("Login") {
            LoginView(navController = navController) // Llama a HomeScreen
        }
        //Las ventanas se encuentran escritas en base a la estructura establecida de como se manejaria
        composable("Home") {
            HomeView(navController = navController) // Llama a DetailScreen
        }
        composable("InfoM") {
            InfoMView(navController = navController) // Llama a SettingsScreen
        }
        composable("Mapa") {
            MapaView(navController = navController)
        }
        //Rutas posibles dentro del apartado de Calendario
        composable("Calendario") {
            CalendarView(navController = navController) // Llama a CalendarView
        }
        composable("Talleres") {
            TalleresView(navController = navController) // Llama a TalleresView
        }
        composable("Materias") {
            MateriasView(navController = navController) // Llama a SettingsScreen
        }
        //Rutas posibles dentro del apartado de Perfil
        composable("Profile"){
            ProfileView(navController = navController)
        }
        composable("Configuraciones") {
            SettingsView(navController = navController) // Llama a SettingsScreen
        }
    }

}