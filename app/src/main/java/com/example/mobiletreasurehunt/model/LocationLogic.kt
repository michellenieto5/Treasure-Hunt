package com.example.mobiletreasurehunt.model

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationLogic(private val context: Context) {
    private val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    fun getCurrentLocation(callback: (android.location.Location?) -> Unit) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("LocationLogic", "Location permissions not granted")
            callback(null)
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    Log.d("LocationLogic", "Location received: Latitude = ${location.latitude}, Longitude = ${location.longitude}")
                } else {
                    Log.d("LocationLogic", "Location received is null")
                }
                callback(location)
            }
            .addOnFailureListener { exception ->
                Log.d("LocationLogic", "Failed to get location: ${exception.message}")
                callback(null)
            }
    }
}
