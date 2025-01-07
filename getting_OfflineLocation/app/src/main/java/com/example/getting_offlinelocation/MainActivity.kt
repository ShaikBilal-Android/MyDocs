package com.example.getting_offlinelocation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity(), LocationListener {

    private lateinit var locationManager: LocationManager
    private lateinit var latitudeTextView: TextView
    private lateinit var longitudeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Request location permissions
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 1)
        } else {
            // Initialize location manager
            locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
            val isGpsProviderAvailable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val isNetworkProviderAvailable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            if (isGpsProviderAvailable){
                Toast.makeText(this,"GPS Provider is Available...",Toast.LENGTH_SHORT).show()
            }else if (isNetworkProviderAvailable){
                Toast.makeText(this,"Network Provider is Available...",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"No Provider is Available...",Toast.LENGTH_SHORT).show()
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Initialize location manager
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
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
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
            Toast.makeText(this, "Location permissions Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Location permissions denied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onLocationChanged(location: Location) {
        val latitude = location.latitude
        val longitude = location.longitude
        // Update the TextViews with the latitude and longitude
        latitudeTextView.text = "Latitude: $latitude"
        try {
            longitudeTextView.text = "Longitude: $longitude"
        }catch (e:Exception){
            Log.d("log-Longitude","Error occurred while processing longitude",e)
        }catch (f:Exception){

        }
        // Do something with latitude and longitude, e.g., display them on a map or save them to a database
        Toast.makeText(this, "Latitude: $latitude, Longitude: $longitude", Toast.LENGTH_SHORT).show()
        Log.d("log-Latitude","Lotitude is $latitude")
    }

    override fun onProviderDisabled(provider: String) {
        // Handle provider disabled
    }

    override fun onProviderEnabled(provider: String) {
        // Handle provider enabled
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle?) {
        // Handle status changed
    }
}