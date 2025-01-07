package com.example.fusedlocation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.location.FusedLocationProviderClient

class LocationHelper : AppCompatActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val REQUEST_LOCATION_PERMISSION = 100
    private lateinit var txtLat: TextView
    private lateinit var txtLon: TextView

    private val myLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            val location = locationResult.lastLocation
            if (location != null) {
                val latitude = location.latitude
                val longitude = location.longitude
                txtLat.text = "Latitude: $latitude"
                txtLon.text = "Longitude: $longitude"
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_helper)

        txtLat = findViewById(R.id.txtLat)
        txtLon = findViewById(R.id.txtLon)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        if (checkLocationPermission()) {
            requestLocationUpdates()
        } else {
            requestLocationPermission()
        }
    }

    private fun checkLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_LOCATION_PERMISSION
        )
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationProviderClient.requestLocationUpdates(
            LocationRequest.create().apply {
                interval = 10000 // 10 seconds
                fastestInterval = 5000 // 5 seconds
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            },
            myLocationCallback,
            Looper.getMainLooper()
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocationUpdates()
            }
        }
    }
}