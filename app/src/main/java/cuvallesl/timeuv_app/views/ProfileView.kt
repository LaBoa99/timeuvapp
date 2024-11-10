package cuvallesl.timeuv_app.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.navigation.NavHostController
import cuvallesl.timeuv_app.R


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")


@Composable
fun ProfileView(navController: NavHostController){

    Scaffold(
        topBar = { //Se puede agregar un AppBar con el saccafoll de la siguiente manera
            CenterAlignedTopAppBar(
                title = {Text(
                    text = ""
                )},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor =  Color.White
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {navController.navigate("Home")}
                    ){
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {navController.navigate("Configuraciones")}
                    ){
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )
        },

        bottomBar = {
            NavigationBar(
                modifier = Modifier.height(100.dp)
            ){
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = false,
                    onClick = { navController.navigate("Home") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Settings") },
                    label = { Text("Calendar") },
                    selected = false,
                    onClick = {navController.navigate("Calendario")}
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = true,
                    onClick = { }
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.Red)

    ){
        Text(text = "Editar Perfil")
        ContentProfileView()
    }
}

@Composable
fun ContentProfileView(){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 90.dp)
            .statusBarsPadding()
        , verticalArrangement =Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally


    ){
        IconProfile(){
            //Aqui va la funcionalidad OnClick
        }
        MainTextField(text = "Nombre") { }
        MainTextField(text = "Correo") { }
        MainTextField(text = "Contraseña") { }
    }
}


@Composable
fun IconProfile(onClick:()-> Unit){
    Box(
        modifier = Modifier
            .padding(bottom = 24.dp)
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.Center),
                tint = Color.Gray
            )
        }
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 8.dp, y = (-8).dp)
                .background(Color.White, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar foto",
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun MainTextField(text: String, onTextChange: (String)-> Unit){

    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        label = {Text(text = "$text")},
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    )
}