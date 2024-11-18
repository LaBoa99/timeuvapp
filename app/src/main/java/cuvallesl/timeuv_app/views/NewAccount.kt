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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cuvallesl.timeuv_app.R
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewAccView(navController: NavHostController) {
    Scaffold {
        ContentNAView(navController)
    }
}

@Composable
fun ContentNAView(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderImageNA()
        Spacer(modifier = Modifier.padding(26.dp))
        EmailFieldNA()
        Spacer(modifier = Modifier.padding(1.dp))
        PaswordFieldNA()
        ButtonLoginNA(navController) // Pasamos navController aquí
    }
}

@Composable
fun ButtonLoginNA(navController: NavHostController) {
    Button(
        onClick = { navController.navigate("Home") },
        modifier = Modifier
            .width(150.dp)
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(Color.Red),
        content = {
            Text(text = "Crear Cuenta")
        }
    )
}
@Composable
fun PaswordFieldNA() {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = { Text(text = "Pasword") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(20.dp)
    )
}
@Composable
fun EmailFieldNA() {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
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
