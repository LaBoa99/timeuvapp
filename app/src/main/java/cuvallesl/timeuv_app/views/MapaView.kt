package cuvallesl.timeuv_app.views

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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

    // Configuración inicial de OSMdroid con SharedPreferences
    LaunchedEffect(Unit) {
        Configuration.getInstance().load(context, context.getSharedPreferences("map_prefs", Context.MODE_PRIVATE))
        Configuration.getInstance().userAgentValue = "TimeUV-App"
    }

    // Crear y mostrar el MapView usando AndroidView, con gestión de ciclo de vida
    var mapView by remember { mutableStateOf<MapView?>(null) }

    DisposableEffect(Unit) {
        onDispose {
            mapView?.onPause()
            mapView?.onDetach()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Mapa
        AndroidView(
            factory = { ctx ->
                MapView(ctx).apply {
                    mapView = this
                    setTileSource(TileSourceFactory.MAPNIK)
                    controller.setZoom(15.0)
                    controller.setCenter(GeoPoint(20.5364, -103.9681)) // Coordenadas de CUValles

                    // Agregar marcador para la biblioteca
                    val marker = Marker(this)
                    marker.position = GeoPoint(20.536277973915965, -103.96617593519974)
                    marker.title = "Biblioteca"
                    marker.snippet = "Biblioteca CUValles"
                    overlays.add(marker)

                    onResume() // Resumir el mapa para evitar problemas de visualización
                }
            },
            modifier = Modifier.weight(1f)
        ) { map ->
            mapView = map
            map.onResume() // Asegura que el mapa esté activo al mostrar la vista
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
