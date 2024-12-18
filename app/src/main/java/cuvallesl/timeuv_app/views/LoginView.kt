package cuvallesl.timeuv_app.views

import   android.annotation.SuppressLint
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
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import cuvallesl.timeuv_app.fcm.getTokenFromPreferences
import cuvallesl.timeuv_app.models.LoginResponse
import cuvallesl.timeuv_app.models.User
import cuvallesl.timeuv_app.models.UserCreateRequest
import cuvallesl.timeuv_app.network.token.TokenStore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginView(email:String,materia:String,navController: NavHostController) {
    Scaffold {
        ContentLoginView(email,materia,navController)
    }
}

@Composable
fun ContentLoginView(email:String,materia:String,navController: NavHostController) {
    val apiService = ApiClient.apiService
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var correo by remember { mutableStateOf("") }
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
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
        )
        //EmailTextField("Email", onTextChanged = { text -> inputText.value = text})
        Spacer(modifier = Modifier.padding(15.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
        )
        //PaswordTextField("Pasword", onTextChanged = { text -> inputText.value = text})
        //ButtonLogin(navController) // Pasamos navController aquí
        Spacer(modifier = Modifier.padding(15.dp))
        Button(
            onClick = {
                scope.launch {
                    try {
                        //if(correo == "Home" && password == "Home"){
                        val request = LoginRequest(correo, password)
                        val call = ApiClient.apiService.login(request)
                        call.enqueue(
                            object : Callback<LoginResponse> {
                                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                                    if (response.isSuccessful) {
                                        val post = response.body()
                                        println(post)
                                        post?.access_token?.let { TokenStore.saveToken(it) }
                                        navController.navigate("Home/$correo/$materia")
                                    } else {
                                        Toast.makeText(context,"Contrasena incorrecta",Toast.LENGTH_SHORT ).show()
                                    }
                                }

                                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                                    Toast.makeText(context,"Contrasena incorrecta",Toast.LENGTH_SHORT ).show()
                                }
                            }
                        )
                    } catch (e: Exception) {
                        errorMessage = "Error: ${e.message}"
                    }
                }
            },
            modifier = Modifier.width(220.dp).padding(4.dp)
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFD32F2F))
        ) {
            Text("Login")
        }
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage)
        }
        ButtonNA(email,materia,navController) // Pasamos navController aquí
    }
}

/*Funcion obsoleta
@Composable
fun ButtonLogin(email:String,materia:String,navController: NavHostController) {
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
 */
@Composable
fun ButtonNA(email: String,materia: String,navController: NavHostController) {
    Button(
        onClick = { navController.navigate("NewAccount/$email/$materia") },
        modifier = Modifier
            .width(200.dp)
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFFD32F2F)),
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

/*Funciones obsoleta con la implementacion de la api/-------------------------------
@Composable
fun PasswordTextField(label: String, onTextChanged: (String) -> Unit) {

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
 */