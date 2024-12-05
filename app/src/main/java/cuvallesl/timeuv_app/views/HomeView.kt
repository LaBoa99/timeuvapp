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
fun HomeView(email:String,materia:String, navController: NavHostController){
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
            .background(Color.Red)

    ) {
        ContentHomeView(email,materia,navController)
    }
}

@Composable
fun ContentHomeView(email:String,materia:String, navController: NavHostController){
    val SM1 = "Teoria de Control"//Componentes de la materia 1
    val RM1 = "Aula C3"
    val TM1 = "Mar 08:00 - 10:00"
    val SM2 = "Algoritmos"//Componentes de la materia 2
    val RM2 = "Aula B1"
    val TM2 = "Jue 16:00 - 18:00"
    val SM3 = "Automatizacion"//Componentes de la materia 3
    val RM3 = "Aula C2"
    val TM3 = "Mar 12:00 - 14:00"
    val SM4 = "Varias Variables"//Componentes de la materia 4
    val RM4 = "Aula A 10"
    val TM4 = "Mar 14:00 - 16:00"
    val SM5 = "Toxicologia"//Componentes de la materia 5
    val RM5 = "Aula Movil 1"
    val TM5 = "Lun 16:00 - 18:00"
    val SM6 = "Biomateriales"//Componentes de la materia 6
    val RM6 = "Aula C14"
    val TM6 = "Mie 08:00 - 10:00"
    val SM7 = "Bioinformatica"//Componentes de la materia 7
    val RM7 = "Aula Cita 2"
    val TM7 = "Mie 14:00 - 16:00"
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
            text = "Materias",style = MaterialTheme.typography.headlineSmall,color = Color.Black,
            modifier = Modifier
                .align(Alignment.Start)
                .background(Color.White, shape = RoundedCornerShape(topStart = 16.dp))
                .fillMaxSize()
                .padding(10.dp)
        )

        Space(10)

        Text(
            text = "Tus Materias",
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
            //Los parametros enviados a la otra ventana se mantienen, lo unico modificable sera la informacion enviada a la ventana, como numero de materia para la extraccion de datos, la estructura de los datacards se mantiene
            if(email == "Home") {
                SubjectCard(subject = SM1,room = RM1,time = TM1) {
                    val im = "1"
                    navController.navigate("InfoM/$email/$im")
                }
                Spacer(modifier = Modifier.width(20.dp))
                SubjectCard(subject = SM2,room = RM2,time = TM2) {
                    val im = "2"
                    navController.navigate("InfoM/$email/$im")
                }
                Spacer(modifier = Modifier.width(20.dp))
                SubjectCard(subject = SM3,room = RM3,time = TM3) {
                    val im = "3"
                    navController.navigate("InfoM/$email/$im")
                }
                Spacer(modifier = Modifier.width(20.dp))
                SubjectCard(subject = SM4,room = RM4,time = TM4,
                ) {
                    val im = "4"
                    navController.navigate("InfoM/$email/$im")
                }
            }
            else{ //Se establece como si fuese el perfil de un estudiante de Sistemas Biologicos
                SubjectCard(subject = SM5,room = RM5,time = TM5) {
                    val im = "5"
                    navController.navigate("InfoM/$email/$im")
                }
                Spacer(modifier = Modifier.width(20.dp))
                SubjectCard(subject = SM2,room = RM2,time = TM2,) {
                    val im = "2"
                    navController.navigate("InfoM/$email/$im")
                }
                Spacer(modifier = Modifier.width(20.dp))
                SubjectCard(subject = SM6,room = RM6,time = TM6) {
                    val im = "6"
                    navController.navigate("InfoM/$email/$im")
                }
                Spacer(modifier = Modifier.width(20.dp))
                SubjectCard(subject = SM7,room = RM7,time = TM7
                ) {
                    val im = "7"
                    navController.navigate("InfoM/$email/$im")
                }
            }

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
        ScheduleCard(name = "Andrea")
        SubjectCard(subject = SM3,room = RM3,time = TM3) {
            val im = "3"
            navController.navigate("InfoM/$email/$im")
        }
        Spacer(modifier = Modifier.width(20.dp))
        SubjectCard(subject = SM2,room = RM2,time = TM2) {
            val im = "2"
            navController.navigate("InfoM/$email/$im")
        }
        Spacer(modifier = Modifier.width(20.dp))
        SubjectCard(subject = SM3,room = RM3,time = TM3) {
            val im = "3"
            navController.navigate("InfoM/$email/$im")
        }
        Spacer(modifier = Modifier.width(20.dp))
        SubjectCard(subject = SM4,room = RM4,time = TM4,
        ) {
            val im = "4"
            navController.navigate("InfoM/$email/$im")
        }
        Space(20)
        ScheduleCard(name = "Claudio")
        Space(20)
        ScheduleCard(name = "Enrique")
        Space(20)
        ScheduleCard(name = "Humberto")
        Space(20)
        ScheduleCard(name = "Vladimir")
        SubjectCard(subject = SM1,room = RM1,time = TM1) {
            val im = "1"
            navController.navigate("InfoM/$email/$im")
        }
        Spacer(modifier = Modifier.width(20.dp))
        SubjectCard(subject = SM3,room = RM3,time = TM3) {
            val im = "3"
            navController.navigate("InfoM/$email/$im")
        }
        Spacer(modifier = Modifier.width(20.dp))
        SubjectCard(subject = SM4,room = RM4,time = TM4,
        ) {
            val im = "4"
            navController.navigate("InfoM/$email/$im")
        }
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
