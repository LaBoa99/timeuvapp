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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cuvallesl.timeuv_app.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Preview
@Composable
fun LoginView(){
    Scaffold{
        ContentLoginView()
    }
}
@Composable
fun ContentLoginView(){

    Column (modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        HeaderImage()
        Spacer(modifier = Modifier.padding(26.dp))
        EmailField()
        Spacer(modifier = Modifier.padding(1.dp))
        PaswordField()
        ButtonLogin()
    }
}

@Composable
fun ButtonLogin() {
    Button(
        onClick = {},
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
fun PaswordField() {
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
fun EmailField() {
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
fun HeaderImage(){
    Image(
        painter = painterResource(id = R.drawable.download),
        contentDescription ="Header",
        modifier = Modifier
            .width(250.dp)
            .height(250.dp)
    )
}