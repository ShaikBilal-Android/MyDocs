package com.example.googlebooks
//
//import androidx.appcompat.app.AppCompatActivity
//
//class DisplayData : AppCompatActivity() {
////    lateinit var tv:TextView
//    lateinit var recycle:RecyclerView
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_display_data)
//
//        // Catch the intent and extract te data of it
////        tv = findViewById(R.id.textView)
//        recycle = findViewById(R.id.item_recylerview)
//        val response = intent.getStringExtra("RES")
//
//        // JSON Parsing
//        val root = JSONObject(response!!)
//        val itemsArray = root.getJSONArray("items")
//        // Now, we need to loop through so that we get the individual items over here
//
//        var i = 0
//        // For Clearing the text box initially
//        tv.text = ""
//        while (i<itemsArray.length()){
//            val item = itemsArray.getJSONObject(i)
//            // Now we are working with individual elements of items Array of json response
//            val volinfo = item.getJSONObject("volumeInfo")
//            val title = volinfo.getString("title")
//            tv.text = tv.text.toString()+"\n"+title
//            i++
//        }
//
//        // From here we will create a Gson object and will try to get the same output using Gson obj
////        val gson = Gson()
////        val sd:SourceData = gson.fromJson(response,SourceData::class.java)
////        val ra:RecyclerAdapter(this)
//    }
//}

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.example.SourceData
import com.google.gson.Gson

class DataDisplayActivity : AppCompatActivity() {
    /*lateinit var tv:TextView*/
//    lateinit var recycle:RecyclerView
    lateinit var recycle: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_data
        // Catch the intent and extract data out of it
        /*tv = findViewById(R.id.textView)*/
        recycle = findViewById(R.id.recyclerview)
        val response = intent.getStringExtra("RES")
        /* // JSON Parsing
         val root = JSONObject(response!!)
         val itemsArray = root.getJSONArray("items")
         // Now, we need to loop through so that we get individual Items
         var i = 0
         tv.text = ""
         while(i<itemsArray.length()){
             val item =  itemsArray.getJSONObject(i)
             // You are now working with individual Items in the Items array
             val volInfo = item.getJSONObject("volumeInfo")
             val title = volInfo.getString("title")
             tv.text = tv.text.toString()+"\n"+title
             i++
         }*/

        val gson = Gson()
        val sd: SourceData = gson.fromJson(response,SourceData::class.java)
        var i = 0

        /*
        tv.text = ""
        while(i<sd.items.size){
            val title = sd.items.get(i).volumeInfo?.title
            tv.text = tv.text.toString()+"\n"+title
            i++
        }*/

        val ra = RecycleAdapter(this,sd.items)
        recycle.adapter = ra
        recycle.layoutManager = LinearLayoutManager(this)

    }

    // Char GPT Suggested Code


//    lateinit var recycle: RecyclerView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_display_data)
//
//        recycle = findViewById(R.id.recyclerview)
//        val response = intent.getStringExtra("RES")
//
//        val gson = Gson()
//        val sd: SourceData = gson.fromJson(response, SourceData::class.java)
//
//        val ra = RecycleAdapter(this, sd.items)
//        recycle.adapter = ra
//        recycle.layoutManager = LinearLayoutManager(this)
//    }
}