package cuvallesl.timeuv_app.views

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
import com.google.android.gms.location.*
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
    var currentLocation by remember { mutableStateOf<Location?>(null) }

    // Configuración de FusedLocationProvider para obtener ubicación en tiempo real
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000).build()

    DisposableEffect(Unit) {
        onDispose {
            mapView?.onPause()
            mapView?.onDetach()
        }
    }

    LaunchedEffect(Unit) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(locationRequest, object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    currentLocation = locationResult.lastLocation
                }
            }, context.mainLooper)
        } else {
            Toast.makeText(context, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show()
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
                    val bibliotecaMarker = Marker(this)
                    bibliotecaMarker.position = GeoPoint(20.536277973915965, -103.96617593519974)
                    bibliotecaMarker.title = "Biblioteca"
                    bibliotecaMarker.snippet = "Biblioteca CUValles"
                    overlays.add(bibliotecaMarker)

                    onResume() // Resumir el mapa para evitar problemas de visualización
                }
            },
            modifier = Modifier.weight(1f)
        ) { map ->
            mapView = map
            map.onResume() // Asegura que el mapa esté activo al mostrar la vista

            // Actualizar ubicación en el mapa si se obtiene una ubicación actual
            currentLocation?.let { location ->
                val geoPoint = GeoPoint(location.latitude, location.longitude)

                // Mover la cámara a la ubicación actual
                map.controller.setCenter(geoPoint)

                // Agregar marcador en la ubicación actual
                val userMarker = Marker(map)
                userMarker.position = geoPoint
                userMarker.title = "Mi Ubicación"
                userMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)

                // Eliminar marcadores anteriores y agregar el marcador actualizado
                map.overlays.removeIf { it is Marker && it.title == "Mi Ubicación" }
                map.overlays.add(userMarker)

                map.invalidate() // Refrescar el mapa para mostrar los cambios
            }
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