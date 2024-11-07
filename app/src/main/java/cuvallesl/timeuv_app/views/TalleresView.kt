package cuvallesl.timeuv_app.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

@Composable
fun TalleresView(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        // Header with back arrow and "Registrar Taller" button
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {navController.popBackStack()}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Regresar",
                    tint = Color.Black
                )
            }
            Text(
                text = "Programación para Dispositivos Móviles",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                color = Color.Black
            )
            Button(
                onClick = { /* Acción de opciones */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF05454))
            ) {
                Text(text = "Registrar Taller", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Cards for each subject
        MateriaCard(
            id = "ID994",
            name = "Programación para Dispositivos Móviles",
            professor = "Erick Jorge Roberto Guerrero Núñez",
            credits = "9 Créditos"
        )

        Spacer(modifier = Modifier.height(8.dp))

        TallerCard(
            id = "ID994",
            name = "Programación para Dispositivos Móviles",
            professor = "Erick Jorge Roberto Guerrero Núñez",
            credits = "9 Créditos"
        )
    }
}

@Composable
fun TallerCard(id: String, name: String, professor: String, credits: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFDCDC))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = id,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFFD32F2F)
                )
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    text = "Profesor: $professor",
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = credits,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Ver más",
                tint = Color(0xFFD32F2F)
            )
        }
    }
}