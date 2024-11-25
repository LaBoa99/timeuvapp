package cuvallesl.timeuv_app.views

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cuvallesl.timeuv_app.components.ScheduleCard
import cuvallesl.timeuv_app.components.SubjectCard
import cuvallesl.timeuv_app.R


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")


@Composable
fun HomeView(email:String, navController: NavHostController){
    val materia = "1"
    Scaffold(
        topBar = { //Se puede agregar un AppBar con el saccafoll de la siguiente manera
            CenterAlignedTopAppBar(
                title = {Text(text = "")},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor =  Color(0xFFD32F2F)
                )
            )
            IconButtomHome {
                //Aqui va la funcionalidad del icon
            }
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier.height(100.dp)
            ){
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = true,
                    onClick = { }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Settings") },
                    label = { Text("Calendar") },
                    selected = false,
                    onClick = { navController.navigate("Calendario/$materia") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = false,
                    onClick = { navController.navigate("Profile/$materia") }
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.Red)

    ) {
        ContentHomeView(email,navController)
    }
}

@Composable
fun ContentHomeView(email:String, navController: NavHostController){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(start =0.dp, end = 0.dp)
        ,verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Space(50)

        Box(modifier = Modifier
            .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 16.dp))
            .fillMaxWidth()
            .background(Color(0xFFD32F2F))
            .heightIn(min = 240.dp, max = 300.dp)


        ){
            Image(
                painter = painterResource(id = R.drawable.imagencentral),
                contentDescription = "Conversation",
                modifier = Modifier
                    .height(200.dp)
                    .clip(CircleShape) // Forma de círculo
                    .align(Alignment.CenterEnd)  // Alinea la imagen a la derecha

            )
        }

        Space(20)
        Text(
            text = "Subjects",style = MaterialTheme.typography.headlineSmall,color = Color.Black,
            modifier = Modifier
                .align(Alignment.Start)
                .background(Color.White, shape = RoundedCornerShape(topStart = 16.dp))
                .fillMaxSize()
                .padding(10.dp)
        )

        Space(10)

        Text(
            text = "Your Subjects",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black.copy(alpha = 0.7f),
            modifier = Modifier.align(Alignment.Start).padding(15.dp).background(Color.White)
        )
        Space(20)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())  // Habilita el desplazamiento horizontal
                .padding(vertical = 8.dp)
        ) {

            SubjectCard(subject = "Teoria de Control", room = "Aula C3", time = "08:00 - 10:0" , ){
                var materia = "1"
                navController.navigate("InfoM/$materia")}
            Spacer(modifier = Modifier.width(20.dp))
            SubjectCard(subject = "Algoritmos", room = "Aula B1", time = "16:00 - 18:00", ){
                var materia = "2"
                navController.navigate("InfoM/$materia")}
            Spacer(modifier = Modifier.width(20.dp))
            SubjectCard(subject = "Automatizacion", room = "Aula C2", time = "12:00 - 14:00", ){
                var materia = "3"
                navController.navigate("InfoM/$materia")}
            Spacer(modifier = Modifier.width(20.dp))
            SubjectCard(subject = "Varias Variables", room = "Aula A 10", time = "14:00 - 18:00",){
                var materia = "4"
                navController.navigate("InfoM/$materia")}

        }
        Space(20)
        Text(
            text = "Your Shedule",style = MaterialTheme.typography.headlineSmall,color = Color.Black,
            modifier = Modifier.align(Alignment.Start).padding(10.dp)
        )

        Space(10)

        Text(
            text = "All saved shedules",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black.copy(alpha = 0.7f),
            modifier = Modifier.align(Alignment.Start).padding(15.dp)
        )
        Space(20)
        ScheduleCard(name = "Jhon")
        Space(20)
        ScheduleCard(name = "Pedro")
        Space(20)
        ScheduleCard(name = "Juan")
        Space(20)
        ScheduleCard(name = "Monica")
        Space(80)


    }
}
@Composable
fun Space(size: Int){
    Spacer(modifier = Modifier.height(size.dp))
}

@Composable
fun IconButtomHome(onClick:()-> Unit){
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Buscar",
            tint = Color.White,
            modifier = Modifier
                .size(35.dp)
        )
    }
}
