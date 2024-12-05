package cuvallesl.timeuv_app.views

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cuvallesl.timeuv_app.components.SubjectCard
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarView(email:String,materia: String,navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Calendario de actividades",
                        color = Color.White,
                        fontSize = 20.sp
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
                    onClick = { navController.navigate("Home/$email/$materia") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Settings") },
                    label = { Text("Calendar") },
                    selected = true,
                    onClick = { /* Actual */ }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = false,
                    onClick = { navController.navigate("Profile/$email/$materia") }
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.Red)
    ) {
        ContentCalendarView(email,materia,navController)
    }
}



@Composable
fun ContentCalendarView(email:String,materia: String,navController: NavHostController) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    val context = LocalContext.current
    val events = mapOf(
        LocalDate.now() to "Entrega Dispositivos Moviles",
        LocalDate.now().plusDays(5) to "Informacion de Practicas",
        LocalDate.now().plusDays(8) to "Conferencia Semiconductores"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
            .padding(top = 40.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Título del mes con navegación
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())  // Habilita el desplazamiento horizontal
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "‹",
                modifier = Modifier
                    .clickable { currentMonth = currentMonth.minusMonths(1) }
                    .padding(8.dp),
                fontSize = 24.sp,
                color = Color(0xFFB71C1C)
            )
            Text(
                text = currentMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale("es"))),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                "›",
                modifier = Modifier
                    .clickable { currentMonth = currentMonth.plusMonths(1) }
                    .padding(8.dp),
                fontSize = 24.sp,
                color = Color(0xFFB71C1C)
            )
        }

        // Días de la semana
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf("D", "L", "M", "M", "J", "V", "S").forEach { day ->
                Text(
                    text = day,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 16.sp
                )
            }
        }

        // Calendario actualizado
        CalendarGrid(
            currentMonth = currentMonth,
            selectedDate = selectedDate,
            onDateSelected = { selectedDate = it },
            events = events
        )

        // Botones de Navegación a Talleres y Materias
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())  // Habilita el desplazamiento horizontal
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.navigate("Talleres/$email/$materia") },
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .padding(end = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB71C1C)
                )
            ) {
                Text(
                    "Ver Talleres",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }

            Button(
                onClick = { navController.navigate("Materias/$email/$materia") },
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .padding(start = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB71C1C)
                )
            ) {
                Text(
                    "Ver Materias",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
            SubjectCard(subject = "Entrega Dispositivos Mov" ,room = "En Linea",time = "15:00"){ Toast.makeText(context,"Este evento es en linea",Toast.LENGTH_SHORT ).show()}
            Spacer(modifier = Modifier.width(20.dp))
            SubjectCard(subject = "Informacion Practicas" ,room = "Auditorio CITA",time = "12:00"){ val im = "0"
                navController.navigate("Mapa/$email/$im")}
            Spacer(modifier = Modifier.width(20.dp))
            SubjectCard(subject = "Conferencia Semiconductores" ,room = "Auditorio CITA / En Linea",time = "10:00"){val im = "0"
                navController.navigate("Mapa/$email/$im")}

        }

    }
}

@Composable
fun CalendarGrid(
    currentMonth: YearMonth,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    events: Map<LocalDate, String> // Mapa de fechas a eventos
) {
    val firstDayOfMonth = currentMonth.atDay(1)
    val daysInMonth = currentMonth.lengthOfMonth()
    val startDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        var currentDay = 1

        repeat((daysInMonth + startDayOfWeek + 6) / 7) { week ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(7) { dayOfWeek ->
                    if (week == 0 && dayOfWeek < startDayOfWeek) {
                        // Espacios vacíos antes del primer día
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .padding(2.dp)
                        )
                    } else if (currentDay <= daysInMonth) {
                        val date = currentMonth.atDay(currentDay)
                        val isSelected = date == selectedDate
                        val hasEvent = events.containsKey(date)

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .padding(2.dp)
                                .background(
                                    color = when {
                                        isSelected -> Color(0xFFB71C1C) // Color de selección
                                        hasEvent -> Color(0xFFFFEB3B) // Color para eventos
                                        else -> Color.Transparent
                                    },
                                    shape = CircleShape
                                )
                                .clickable { onDateSelected(date) },
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = currentDay.toString(),
                                    fontSize = 16.sp,
                                    color = if (isSelected) Color.White else Color.Black
                                )
                                if (hasEvent) {
                                    Text(
                                        text = "•", // Marcador de evento
                                        fontSize = 20.sp,
                                        color = Color.Red
                                    )
                                }
                            }
                        }
                        currentDay++
                    } else {
                        // Espacios vacíos después del último día
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .padding(2.dp)
                        )
                    }
                }
            }
        }
    }
}