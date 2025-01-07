package com.example.mobiledetails.Mydata

import com.example.example.Mobiledata
import retrofit2.Call
import retrofit2.http.GET

interface Myinterface {

    // Creating a interface
    @GET("products")  // Provide the end points of your URL
    fun mobileDataInterface():Call<Mobiledata>
}