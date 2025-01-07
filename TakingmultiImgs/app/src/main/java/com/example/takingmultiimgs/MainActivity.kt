package com.example.takingmultiimgs

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var captureIV:ImageView
    private lateinit var imgUrl:Uri
    lateinit var capturebtn: Button
    private val contracts = registerForActivityResult(ActivityResultContracts.TakePicture()){
        captureIV.setImageURI(null)
        captureIV.setImageURI(imgUrl)
    }
    val REQUEST_CODE=100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        imgUrl = createImageUri()
        captureIV = findViewById(R.id.capture_img)
        capturebtn = findViewById(R.id.button)
        capturebtn.setOnClickListener {
            contracts.launch(imgUrl)
        }
    }
    private fun createImageUri():Uri{
        val image = File(filesDir,"camera_photos.png")
        return FileProvider.getUriForFile(this,"com.example.takingmultiimgs.FileProvider",image)
    }


}