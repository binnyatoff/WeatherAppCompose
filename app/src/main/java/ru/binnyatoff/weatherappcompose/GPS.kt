package ru.binnyatoff.weatherappcompose

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.binnyatoff.weatherappcompose.data.models.Coordinates
import javax.inject.Inject

class GPS (private var appContext: Context) {

    private val locationManager =
        appContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    private val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

    private val _location = MutableSharedFlow<Coordinates>(replay = 2, extraBufferCapacity = 2, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val location: SharedFlow<Coordinates> = _location.asSharedFlow()

    fun getLocate() {
        if (ActivityCompat.checkSelfPermission(
                appContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                appContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            val lastKnownLocationByGps =
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

            val lastKnownLocationByNetwork =
                locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

            if (hasGps) {
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    500000,
                    1F
                ) { location ->
                    CoroutineScope(Dispatchers.IO).launch {
                        if (location != lastKnownLocationByGps) {
                            compare(lastKnownLocationByGps, location)
                        }
                    }
                }
            }

            if (hasNetwork) {
                locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    500000,
                    1F
                ) { location ->
                    CoroutineScope(Dispatchers.IO).launch {
                        if (location != lastKnownLocationByNetwork) {
                            compare(lastKnownLocationByGps, location)
                        }
                    }
                }
            }
        }
    }

    private suspend fun compare(oldLocation: Location?, newLocation: Location) {
        val newCoordinates = Coordinates(
            newLocation.latitude,
            newLocation.longitude
        )
        if (oldLocation != null) {
            val oldCoordinates = Coordinates(
                oldLocation.latitude,
                oldLocation.longitude
            )
            if (newCoordinates != oldCoordinates) {
                _location.emit(newCoordinates)
            }
        }
    }
}