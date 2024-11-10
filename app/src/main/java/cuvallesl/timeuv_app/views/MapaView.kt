package cuvallesl.timeuv_app.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.maps.android.compose.MarkerState
import com.google.android.gms.maps.model.CameraPosition

//Modificar Completamente debido a errores de funcionamiento y navegacion
@Composable
fun MapaView(navController: NavHostController) {
    // Estado del mapa
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(20.5364, -103.9681), // Coordenadas de ejemplo (Centro Universitario de los Valles)
            12f // Nivel de zoom
        )
    }

    // Mapa de Google con marcador
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = LatLng(20.536277973915965, -103.96617593519974)), // Posición del marcador
            title = "Biblioteca",
            snippet = "Biblioteca CUValles"
        )
    }

    // Botón para regresar
    Button(
        onClick = { navController.navigate("InfoM")  },
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Regresar")
    }
}
