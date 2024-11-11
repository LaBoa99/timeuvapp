package cuvallesl.timeuv_app.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
fun MapaView(navController: NavHostController) {
    val context = LocalContext.current

    // Configuración inicial de OSMdroid fuera del bloque de AndroidView
    remember {
        Configuration.getInstance().userAgentValue = "TimeUV-App"
    }

    // Crear y mostrar el MapView usando AndroidView
    AndroidView(
        factory = { ctx ->
            MapView(ctx).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                controller.setZoom(15.0)
                controller.setCenter(GeoPoint(20.5364, -103.9681)) // Coordenadas de CUValles
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { map ->
        // Agregar marcador para la biblioteca
        val marker = Marker(map)
        marker.position = GeoPoint(20.536277973915965, -103.96617593519974)
        marker.title = "Biblioteca"
        marker.snippet = "Biblioteca CUValles"
        map.overlays.add(marker)
        map.onResume()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Mapa
        AndroidView(
            factory = { MapView(context) },
            modifier = Modifier.weight(1f)
        )

        // Botón para regresar
        Button(
            onClick = { navController.navigate("InfoM") },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Regresar")
        }
    }
}