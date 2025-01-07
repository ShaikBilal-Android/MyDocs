package com.example.polyline

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ployline.R
//import com.example.polyline.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap // Declare mMap as a class variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFrag = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFrag.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap // Initialize mMap when the map is ready
        drawPolyline() // Call drawPolyline() after mMap is initialized
    }

    private fun drawPolyline() {
        val polylineOptions = PolylineOptions()
            .add(LatLng(16.5187, 80.6200))
            .add(LatLng(16.4981, 80.6434))
            // Add more points as needed
            .width(5f)
            .color(Color.BLUE)

        mMap.addPolyline(polylineOptions) // Use mMap to add the polyline
    }
}
