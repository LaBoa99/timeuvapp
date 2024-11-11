package cuvallesl.timeuv_app.views

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.ArrowForward
import androidx.navigation.NavHostController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsView(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("CONFIGURACIONES") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Profile") }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "CUENTA",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Opciones de Cuenta
            SettingsItem(
                text = "CAMBIAR CONTRASEÑA",
                hasArrow = true
            )
            SettingsItem(
                text = "IDIOMAS",
                hasArrow = true
            )
            SettingsItem(
                text = "PRIVACIDAD Y SEGURIDAD",
                hasArrow = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Sección Notificaciones
            Text(
                text = "NOTIFICACIONES",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Opciones de Notificaciones
            SettingsToggleItem(
                text = "NOTIFICACIONES",
                initialState = true
            )
            SettingsToggleItem(
                text = "NOTIFICACIONES DE APP",
                initialState = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Sección Invitar Amigos
            Text(
                text = "INVITAR AMIGOS",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Botón Cerrar Sesión
            Button(
                onClick = { navController.navigate("Login") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB22222)
                )
            ) {
                Text("CERRAR SESIÓN", color = Color.White)
            }
        }
    }
}

@Composable
fun SettingsItem(
    text: String,
    hasArrow: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text)
        if (hasArrow) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Ir a $text"
            )
        }
    }
}

@Composable
fun SettingsToggleItem(
    text: String,
    initialState: Boolean = false
) {
    var isChecked by remember { mutableStateOf(initialState) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text)
        Switch(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
    }
}