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
            LoginView(navController = navController, context = LocalContext.current) // Llama a HomeScreen
        }
        //Las ventanas se encuentran escritas en base a la estructura establecida de como se manejaria
        composable("Home") {
            HomeView(navController = navController, context = LocalContext.current) // Llama a DetailScreen
        }
        composable("InfoM") {
            InfoMView(navController = navController, context = LocalContext.current) // Llama a SettingsScreen
        }
        composable("Mapa") {
            MapaView(navController = navController, context = LocalContext.current)
        }
        //Rutas posibles dentro del apartado de Calendario
        composable("Calendario") {
            CalendarView(navController = navController, context = LocalContext.current) // Llama a CalendarView
        }
        composable("Talleres") {
            TalleresView(navController = navController, context = LocalContext.current) // Llama a TalleresView
        }
        composable("Materias") {
            MateriasView(navController = navController, context = LocalContext.current) // Llama a SettingsScreen
        }
        //Rutas posibles dentro del apartado de Perfil
        composable("Profile"){
            ProfileView(navController = navController, context = LocalContext.current)
        }
        composable("Configuraciones") {
            SettingsView(navController = navController, context = LocalContext.current) // Llama a SettingsScreen
        }
    }

}