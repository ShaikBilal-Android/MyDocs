package com.example.activitylifecycle

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Log.d("MainActivity","OnCreate() Created")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity","OnStart() Created")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity","OnResume() Created")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity","OnPouse() Created")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity","OnStop() Created")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity","OnResume() Created")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity","OnDestroy() Created")
    }

}