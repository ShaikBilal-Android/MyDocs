package com.example.imagewithlatlong

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var imageView: ImageView
    private lateinit var txtLat: TextView
    private lateinit var txtLon: TextView
    private lateinit var btnCapture: Button

    private var latitude: Double? = null
    private var longitude: Double? = null

    private val myLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            val location = locationResult.lastLocation
            if (location != null) {
                latitude = location.latitude
                longitude = location.longitude
                txtLat.text = "Latitude: $latitude"
                txtLon.text = "Longitude: $longitude"
            } else {
                Log.e("MainActivity", "Location is null")
            }
        }
    }

    private val REQUEST_LOCATION_PERMISSION = 100
    private val REQUEST_CAMERA_PERMISSION = 101

    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            handleImageCaptureResult(result.data)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        txtLat = findViewById(R.id.txtLat)
        txtLon = findViewById(R.id.txtLon)
        btnCapture = findViewById(R.id.btnCapture)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        btnCapture.setOnClickListener {
            if (checkCameraPermission()) {
                captureImage()
            } else {
                requestCameraPermission()
            }
        }

        if (checkLocationPermission()) {
            requestLocationUpdates()
        } else {
            requestLocationPermission()
        }
    }

    private fun checkCameraPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            REQUEST_CAMERA_PERMISSION
        )
    }

    private fun captureImage() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(intent)
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
        when (requestCode) {
            REQUEST_LOCATION_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    requestLocationUpdates()
                }
            }
            REQUEST_CAMERA_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    captureImage()
                }
            }
        }
    }

    private fun handleImageCaptureResult(data: Intent?) {
        val thumbnail: Bitmap? = data?.extras?.get("data") as Bitmap?
        if (thumbnail != null && latitude != null && longitude != null) {
            imageView.setImageBitmap(thumbnail)

            // Save image to external storage
            val imagePath = saveImageToExternalStorage(thumbnail)

            if (imagePath != null) {
                // Save imagePath, latitude, and longitude to the Room database
                saveImageData(imagePath, latitude!!, longitude!!)
            }
        } else {
            Log.e("MainActivity", "Failed to capture image or location is null")
        }
    }

    private fun saveImageToExternalStorage(bitmap: Bitmap): String? {
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return try {
            val imageFile = File.createTempFile("image_", ".jpg", storageDir)
            val outputStream = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            imageFile.absolutePath
        } catch (e: IOException) {
            Log.e("MainActivity", "Failed to save image", e)
            null
        }
    }

    private fun saveImageData(imagePath: String, latitude: Double, longitude: Double) {
        val imageEntity = ImageEntity(imagePath = imagePath, latitude = latitude, longitude = longitude)
        val db = AppDatabase.getDatabase(applicationContext)
        lifecycleScope.launch(Dispatchers.IO) {
            db.imageDao().insertImage(imageEntity)
            Log.d("MainActivity", "Image data saved successfully")
        }
    }
}
