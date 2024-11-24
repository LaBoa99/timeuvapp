package cuvallesl.timeuv_app.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cuvallesl.timeuv_app.R
import cuvallesl.timeuv_app.network.ApiClient
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import cuvallesl.timeuv_app.models.LoginRequest
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginView(navController: NavHostController) {
    Scaffold {
        ContentLoginView(navController)
    }
}

@Composable
fun ContentLoginView(navController: NavHostController) {
    val apiService = ApiClient.apiService
    val scope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderImage()
        Spacer(modifier = Modifier.padding(20.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        //EmailTextField("Email", onTextChanged = { text -> inputText.value = text})
        Spacer(modifier = Modifier.padding(1.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        //PaswordTextField("Pasword", onTextChanged = { text -> inputText.value = text})
        //ButtonLogin(navController) // Pasamos navController aquí
        Button(
            onClick = {
                scope.launch {
                    try {
                        val response = apiService.login(LoginRequest(email, password))
                        // Si el login es exitoso, ejecuta la acción de éxito.
                        navController.navigate("Home")
                    } catch (e: Exception) {
                        errorMessage = "Error: ${e.message}"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage)
        }
        ButtonNA(navController) // Pasamos navController aquí
    }
}

@Composable
fun ButtonLogin(navController: NavHostController) {
    Button(
        onClick = { navController.navigate("Home") },
        modifier = Modifier
            .width(150.dp)
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(Color.Red),
        content = {
            Text(text = "Iniciar Sesion")
        }
    )
}
@Composable
fun ButtonNA(navController: NavHostController) {
    Button(
        onClick = { navController.navigate("NewAccount") },
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
fun HeaderImage(){
    Image(
        painter = painterResource(id = R.drawable.logoinicio),
        contentDescription ="Header",
        modifier = Modifier
            .width(250.dp)
            .height(250.dp)
    )
}

//-------------------------------
@Composable
fun PaswordTextField(label: String, onTextChanged: (String) -> Unit) {

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
fun EmailTextField(label: String, onTextChanged: (String) -> Unit) {

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