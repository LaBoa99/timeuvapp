package cuvallesl.timeuv_app.views

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MateriasView(navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Registro de Materias",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFB71C1C)
                )
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier.height(100.dp)
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = false,
                    onClick = { navController.navigate("Home") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Calendar") },
                    label = { Text("Calendar") },
                    selected = false,
                    onClick = { navController.navigate("Calendario") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = false,
                    onClick = { navController.navigate("Profile") }
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5))
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Cards para cada materia
            MateriaCard(
                id = "ID994",
                name = "PROGRAMACION PARA\nDISPOSITIVOS MOVILES",
                professor = "ERICK JORGE ROBERTO GUERRERO\nMUÑOZ",
                credits = "9 CREDITOS"
            )

            Spacer(modifier = Modifier.height(16.dp))

            MateriaCard(
                id = "ID982",
                name = "DISEÑO INTERACTIVO II\n",
                professor = "ERICK JORGE ROBERTO GUERRERO\nMUÑOZ",
                credits = "9 CREDITOS"
            )
            Spacer(modifier = Modifier.height(16.dp))

            MateriaCard(
                id = "D984",
                name = "GEOMETRIA COMPUTACIONAL",
                professor = "CASTILLO CHAVARIN JOSE ADOLFO\n",
                credits = "9 CREDITOS"
            )
            Spacer(modifier = Modifier.height(16.dp))

            MateriaCard(
                id = "ID994",
                name = "PROGRAMACION AVANZADA\n DE PLC",
                professor = "RAMIREZ TORRES MIGUEL\nANGEL",
                credits = "8 CREDITOS"
            )
            Spacer(modifier = Modifier.height(16.dp))

            MateriaCard(
                id = "ID943",
                name = "CONTROLADORES LOGICOS\n PROGRAMABLES",
                professor = "RENTERIA VARGAS ERASMO \nMISAEL",
                credits = "7 CREDITOS"
            )
        }
    }
}

@Composable
fun MateriaCard(
    id: String,
    name: String,
    professor: String,
    credits: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFCDD2))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icono de perfil
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier.size(50.dp),
                    tint = Color.Gray
                )

                // Información de la materia
                Column {
                    Text(
                        text = id,
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = name,
                        fontSize = 13.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "PROFESOR",
                        fontSize = 12.sp,
                        color = Color.DarkGray,
                    )
                    Text(
                        text = professor,
                        fontSize = 11.sp,
                        color = Color.Black
                    )
                    Text(
                        text = credits,
                        fontSize = 12.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Botón de registro
            Button(
                onClick = { /* Acción de registro */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFEEEEEE)
                ),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .height(36.dp)
            ) {
                Text(
                    text = "REGISTRAR",
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}