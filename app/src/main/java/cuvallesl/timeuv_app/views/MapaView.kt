package cuvallesl.timeuv_app.views

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
fun MapaView(email:String,materia:String, navController: NavHostController) {
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
    // Launcher para solicitar el permiso de ubicación
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                // Permiso concedido, iniciar actualizaciones de ubicación
                fusedLocationClient.requestLocationUpdates(locationRequest, object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult) {
                        currentLocation = locationResult.lastLocation
                    }
                }, context.mainLooper)
            } else {
                // Permiso denegado, mostrar un mensaje al usuario
                Toast.makeText(context, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show()
            }
        }
    )

    LaunchedEffect(Unit) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Solicitar el permiso si no está concedido
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            // Permiso ya concedido, iniciar actualizaciones de ubicación
            fusedLocationClient.requestLocationUpdates(locationRequest, object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    currentLocation = locationResult.lastLocation
                }
            }, context.mainLooper)
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
                    val ubiMarker = Marker(this)
                    if(materia == "0") {// Agregar marcador para el auditorio CITA
                        ubiMarker.position =GeoPoint(20.536631465341323, -103.96817731711513)
                        ubiMarker.title = "Auditorio CITA"
                        ubiMarker.snippet = "Auditorio CITA"
                        overlays.add(ubiMarker)
                    }
                    else if (materia in setOf("1", "3", "6")){// Ubicacion del modulo C
                        ubiMarker.position =GeoPoint(20.538155608084757, -103.9665089702658)
                        ubiMarker.title = "Modulo C"
                        ubiMarker.snippet = "Modulo C"
                        overlays.add(ubiMarker)
                    }
                    else if (materia == "2"){// Ubicacion del modulo B
                        ubiMarker.position =GeoPoint(20.536947482043526, -103.9665112257154)
                        ubiMarker.title = "Modulo B"
                        ubiMarker.snippet = "Modulo B"
                        overlays.add(ubiMarker)
                    }
                    else if (materia == "4"){// Ubicacion del modulo A
                        ubiMarker.position =GeoPoint(20.535618954802437, -103.96668489618176)
                        ubiMarker.title = "Modulo A"
                        ubiMarker.snippet = "Modulo A"
                        overlays.add(ubiMarker)
                    }
                    else if (materia == "5"){// Ubicacion de las aulas moviles
                        ubiMarker.position =GeoPoint(20.53463164311687, -103.96785700503061)
                        ubiMarker.title = "Aulas Moviles"
                        ubiMarker.snippet = "Aulas Moviles"
                        overlays.add(ubiMarker)
                    }
                    else if (materia == "7"){// Ubicacion del edificio CITA
                        ubiMarker.position =GeoPoint(20.536698654626818, -103.96815116558675)
                        ubiMarker.title = "CITA"
                        ubiMarker.snippet = "Edificio CITA"
                        overlays.add(ubiMarker)
                        onResume() // Resumir el mapa para evitar problemas de visualización
                    }
                    else{ //Ubicacion de Biblioteca
                        ubiMarker.position =GeoPoint(20.536232970769998, -103.9662989995176)
                        ubiMarker.title = "Biblioteca"
                        ubiMarker.snippet = "Biblioteca CuValles"
                        overlays.add(ubiMarker)
                        onResume() // Resumir el mapa para evitar problemas de visualización
                    }

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
        if (materia == "0")
        {
            Button(
                onClick = { navController.navigate("Calendario/$email/$materia") },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Regresar")
            }
        }else{
            Button(
                onClick = { navController.navigate("InfoM/$email/$materia") },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Regresar")
            }
        }

    }
}