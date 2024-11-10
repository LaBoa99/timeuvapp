package cuvallesl.timeuv_app.views

import android.annotation.SuppressLint
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
fun HomeView(navController: NavHostController){
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
                    onClick = { /* Acción al hacer clic en Home */ }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Settings") },
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
            .background(Color.Red)

    ) {
        ContentHomeView(navController)
    }
}

@Composable
fun ContentHomeView(navController: NavHostController){

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
            SubjectCard(subject = "Mathematics", room = "Room 003A", time = "11:00 - 12:50" , )
            Spacer(modifier = Modifier.width(20.dp))
            SubjectCard(subject = "Byology", room = "Room 004A", time = "13:00 - 15:50", )
            Spacer(modifier = Modifier.width(20.dp))
            SubjectCard(subject = "Phisic", room = "Room 006B", time = "11:00 - 12:50", )
            Spacer(modifier = Modifier.width(20.dp))
            SubjectCard(subject = "Spanish", room = "Room 004A", time = "10:00 - 12:00",)

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
