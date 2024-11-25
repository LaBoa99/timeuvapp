@file:OptIn(ExperimentalMaterial3Api::class)

package cuvallesl.timeuv_app.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InfoMView(materia: String, navController: NavHostController) {
    Scaffold(
        topBar = { //Se puede agregar un AppBar con el saccafoll de la siguiente manera
            CenterAlignedTopAppBar(
                title = {Text(text = "Subject Details",color = Color.White,fontSize = 20.sp)},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor =  Color(0xFFD32F2F)
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {navController.navigate("Home")}
                    ){
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )
        },
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
    ){
        ContentInfoMView(materia,navController)
    }
}

@Composable
fun ContentInfoMView(materia:String, navController: NavHostController) {
    // Main container for the whole screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(35.dp))


        // Materia1
        if(materia =="1" ) {
                Text(
                    text = "Teoria de Control",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )

                // Info cards
                InfoCard(
                    icon = Icons.Default.Place, // Material Icon for location
                    label = "Room",
                    content = "Aula C3"
                )

                InfoCard(
                    icon = Icons.Default.AccessTime, // Material Icon for time
                    label = "Schedule",
                    content = "08:00 - 10:00"
                )

                InfoCard(
                    icon = Icons.Default.Tag, // Material Icon for course code
                    label = "Course Code",
                    content = "182465"
                )
            }
        // Materia2
        else if(materia =="2" ) {
            Text(
                text = "Diseno de Algoritmos",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )

            // Info cards
            InfoCard(
                icon = Icons.Default.Place, // Material Icon for location
                label = "Room",
                content = "Aula B1"
            )

            InfoCard(
                icon = Icons.Default.AccessTime, // Material Icon for time
                label = "Schedule",
                content = "16:00 - 18:00"
            )

            InfoCard(
                icon = Icons.Default.Tag, // Material Icon for course code
                label = "Course Code",
                content = "256487"
            )
        }
        // Materia3
        else if(materia =="3" ) {
            Text(
                text = "Automatizacion Industrial",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )

            // Info cards
            InfoCard(
                icon = Icons.Default.Place, // Material Icon for location
                label = "Room",
                content = "Aula C2"
            )

            InfoCard(
                icon = Icons.Default.AccessTime, // Material Icon for time
                label = "Schedule",
                content = "12:00 - 14:00"
            )

            InfoCard(
                icon = Icons.Default.Tag, // Material Icon for course code
                label = "Course Code",
                content = "212301"
            )
        }
        // Materia4
        else if(materia =="4" ) {
            Text(
                text = "Varias Variables",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )

            // Info cards
            InfoCard(
                icon = Icons.Default.Place, // Material Icon for location
                label = "Room",
                content = "Aula A10"
            )

            InfoCard(
                icon = Icons.Default.AccessTime, // Material Icon for time
                label = "Schedule",
                content = "14:00 - 18:00"
            )

            InfoCard(
                icon = Icons.Default.Tag, // Material Icon for course code
                label = "Course Code",
                content = "212152"
            )
        }
        // Map Button
        Button(
            onClick = { navController.navigate("Mapa")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
        ) {
            Icon(
                imageVector = Icons.Default.Place,
                contentDescription = "View on Map",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("View on Map", color = Color.White)
        }
    }
}


@Composable
fun InfoCard(icon: ImageVector, label: String, content: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.size(24.dp),
                tint = Color(0xFFD32F2F)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = label, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                Text(text = content, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
