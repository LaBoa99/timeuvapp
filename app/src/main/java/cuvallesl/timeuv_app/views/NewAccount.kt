package cuvallesl.timeuv_app.views

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cuvallesl.timeuv_app.R
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewAccView(email:String,materia:String,navController: NavHostController) {
    Scaffold {
        ContentNAView(email,materia,navController)
    }
}

@Composable
fun ContentNAView(email: String,materia: String,navController: NavHostController) {
    var inputText = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderImageNA()
        Spacer(modifier = Modifier.padding(26.dp))
        EmailFieldNA("Email", onTextChanged = { text -> inputText.value = text})
        Spacer(modifier = Modifier.padding(1.dp))
        PaswordFieldNA("Password", onTextChanged = { text -> inputText.value = text})
        ButtonLoginNA(email,materia,navController) // Pasamos navController aquí
    }
}

@Composable
fun ButtonLoginNA(email:String,materia: String,navController: NavHostController) {
    val correo = "Prueba"
    Button(
        onClick = { navController.navigate("Home/$correo/$materia") },
        modifier = Modifier
            .width(150.dp)
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFFD32F2F)),
        content = {
            Text(text = "Crear Cuenta")
        }
    )
}

@Composable
fun PaswordFieldNA(label: String, onTextChanged: (String) -> Unit) {

    val textState = remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        value = textState.value,
        onValueChange = { newText ->
            textState.value = newText
            onTextChanged(newText.text)
        },
        label = { Text(label) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth().padding(20.dp)
    )

}

@Composable
fun EmailFieldNA(label: String, onTextChanged: (String) -> Unit) {

    val textState = remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        value = textState.value,
        onValueChange = { newText ->
            textState.value = newText
            onTextChanged(newText.text)
        },
        label = { Text(label) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth().padding(20.dp)
    )

}
@Composable
fun HeaderImageNA(){
    Image(
        painter = painterResource(id = R.drawable.logoinicio),
        contentDescription ="Header",
        modifier = Modifier
            .width(250.dp)
            .height(250.dp)
    )
}
