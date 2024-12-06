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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cuvallesl.timeuv_app.R
import cuvallesl.timeuv_app.fcm.getTokenFromPreferences
import cuvallesl.timeuv_app.models.User
import cuvallesl.timeuv_app.models.UserCreateRequest
import cuvallesl.timeuv_app.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewAccView(email:String,materia:String,navController: NavHostController) {
    Scaffold {
        ContentNAView(email,materia,navController)
    }
}

@Composable
fun ContentNAView(email: String,materia: String,navController: NavHostController) {
    var inputEmail = remember { mutableStateOf("") }
    var inputPassword = remember { mutableStateOf("")}
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderImageNA()
        Spacer(modifier = Modifier.padding(26.dp))
        EmailFieldNA("Email", onTextChanged = { text -> inputEmail.value = text})
        Spacer(modifier = Modifier.padding(1.dp))
        PaswordFieldNA("Password", onTextChanged = { text -> inputPassword.value = text})
        ButtonLoginNA(inputEmail.value, inputPassword.value, materia, navController) // Pasamos navController aquí
    }
}

@Composable
fun ButtonLoginNA(email:String, password: String, materia: String, navController: NavHostController) {
    val context = LocalContext.current
    Button(
        onClick = {
            println("C: Cosas")

            val sharedPreferences = context.getSharedPreferences("options", Context.MODE_PRIVATE)
            val notificationsEnabled: Boolean = sharedPreferences.getBoolean("notifications", false)
            val token = if (notificationsEnabled) getTokenFromPreferences(context) else ""
            val request = UserCreateRequest(email, password, fcm = token)

            val call = ApiClient.apiService.createUser(request)
            call.enqueue(
                object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {
                            val post = response.body()
                            println(post)
                            navController.navigate("Login/$email/$materia")
                        } else {
                            // Handle error
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        // Handle failure
                    }
            })


        },
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
