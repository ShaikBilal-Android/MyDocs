package com.example.mobiledetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.example.Mobiledata
import com.example.mobiledetails.Mydata.MyAdapter
import com.example.mobiledetails.Mydata.Myinterface
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.internal.GsonBuildConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


import retrofit2.Retrofit
import retrofit2.create


class MainActivity : AppCompatActivity() {
//     lateinit var tv:TextView
    lateinit var recyclerView: RecyclerView
    lateinit var myadapter:MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recview)

        // Creating an Retrofit object
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Myinterface::class.java)

        var alldata = retrofitBuilder.mobileDataInterface()

        alldata.enqueue(object : Callback<Mobiledata?> {
            override fun onResponse(call: Call<Mobiledata?>, response: Response<Mobiledata?>) {

                var responseBody = response.body()
                val reqdata = responseBody?.products!!

                myadapter = MyAdapter(this@MainActivity,reqdata)
                recyclerView.adapter = myadapter
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
//                val collectDataInSb = StringBuilder()
//
//                for(Mobiledata in reqdata){
//                    collectDataInSb.append(Mobiledata.title + " ")
//                }
//                tv.text = collectDataInSb

            }

            override fun onFailure(call: Call<Mobiledata?>, t: Throwable) {
                Log.d("From Main Activity","Retrofit Response Fail..."+ t.message)
            }
        })
    }
}