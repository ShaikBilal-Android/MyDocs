package com.example.freglifecycle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
//import com.example.fragmentlifecycle.databinding.ActivityMainBinding
import com.example.freglifecycle.FragmentOne
import com.example.freglifecycle.FragmentTwo
import com.example.freglifecycle.R
import com.example.freglifecycle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set the initial fragment
        replaceFragment(FragmentOne())

        // Button to load FragmentOne
        binding.btnFragmentOne.setOnClickListener {
            replaceFragment(FragmentOne())
        }

        // Button to load FragmentTwo
        binding.btnFragmentTwo.setOnClickListener {
            replaceFragment(FragmentTwo())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
        Log.d("MainActivity", "Fragment replaced with: ${fragment::class.java.simpleName}")
    }
}
