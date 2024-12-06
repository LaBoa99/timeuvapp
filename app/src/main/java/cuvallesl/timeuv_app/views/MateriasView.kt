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
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import cuvallesl.timeuv_app.models.Course
import cuvallesl.timeuv_app.models.LoginRequest
import cuvallesl.timeuv_app.models.LoginResponse
import cuvallesl.timeuv_app.models.Schedule
import cuvallesl.timeuv_app.network.ApiClient
import cuvallesl.timeuv_app.network.token.TokenStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ShowToast")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MateriasView(email:String,materia:String,navController: NavHostController) {
    val context = LocalContext.current
    var courses by remember { mutableStateOf<List<Course>>(emptyList()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch(Dispatchers.IO) {
            val response = try {
                ApiClient.apiService.getCourses()
            } catch (error: HttpException){
                return@launch
            } catch (error: Exception) {
                return@launch
            }
            if(response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main){
                    courses = response.body()!!
                }
            }
        }
    }
    // val toast = Toast.makeText(context,"Se manda a Leo",Toast.LENGTH_SHORT )
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
                    onClick = { navController.navigate("Home/$email/$materia") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Calendar") },
                    label = { Text("Calendar") },
                    selected = false,
                    onClick = { navController.navigate("Calendario/$email/$materia") }
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

            for(course in courses){
                MateriaCard(
                    real_id = course.id,
                    id = course.courseKey,
                    name = course.subject,
                    professor = course.professor,
                    credits = course.courseCrn,
                    scope = scope
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}



@Composable
fun MateriaCard(
    real_id: Long,
    id: String,
    name: String,
    professor: String,
    credits: String,
    scope: CoroutineScope
) {
    val context = LocalContext.current

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
                onClick = {
                    scope.launch {
                        try {
                            val call = ApiClient.apiService.enroll(real_id.toString())
                            call.enqueue(
                                object : Callback<Schedule> {
                                    override fun onResponse(call: Call<Schedule>, response: Response<Schedule>) {
                                        if (response.isSuccessful) {
                                            Toast.makeText(context, "Materia registrada con exito", Toast.LENGTH_SHORT).show()
                                        } else {
                                            Toast.makeText(context,"Ocurrio un error",Toast.LENGTH_SHORT ).show()
                                        }
                                    }

                                    override fun onFailure(call: Call<Schedule>, t: Throwable) {
                                        Toast.makeText(context,"Ocurrio un error",Toast.LENGTH_SHORT ).show()
                                    }
                                }
                            )
                        } catch (e: Exception) {
                            println(e)
                        }
                    }
                },
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

