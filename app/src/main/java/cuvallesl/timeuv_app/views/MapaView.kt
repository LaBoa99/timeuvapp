package cuvallesl.timeuv_app.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import android.content.Context

@Composable
fun MapaView(navController: NavHostController, context: Context) {
    // Configuración inicial de OSMdroid
    Configuration.getInstance().userAgentValue = "TimeUV-App"

    // Crear y recordar el MapView
    val mapView = remember {
        MapView(context).apply {
            setTileSource(TileSourceFactory.MAPNIK)
            controller.setZoom(12.0)

            // Coordenadas de CUValles
            val cuvallesPoint = GeoPoint(20.5364, -103.9681)
            controller.setCenter(cuvallesPoint)

            // Agregar marcador para la biblioteca
            val marker = Marker(this)
            marker.position = GeoPoint(20.536277973915965, -103.96617593519974)
            marker.title = "Biblioteca"
            marker.snippet = "Biblioteca CUValles"
            overlays.add(marker)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Mapa
        AndroidView(
            factory = { mapView },
            modifier = Modifier.weight(1f)
        ) { map ->
            map.onResume()
        }

        // Botón para regresar
        Button(
            onClick = { navController.navigate("InfoM") },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Regresar")
        }
    }
}