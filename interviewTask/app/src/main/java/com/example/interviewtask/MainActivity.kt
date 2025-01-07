package com.example.interviewtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {

    private val BASE_URL = "https://jsonplaceholder.typicode.com/"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retBD=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory()
    }

    fun getResData(view: View) {

    }
    fun postResData(view: View) {

    }
}