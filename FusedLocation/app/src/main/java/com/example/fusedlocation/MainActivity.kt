package com.example.fusedlocation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnNext:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        btnNext =findViewById(R.id.btnNext)


        btnNext.setOnClickListener {
            val intent = Intent(this,LocationHelper::class.java)
            startActivity(intent)
        }

    }
}