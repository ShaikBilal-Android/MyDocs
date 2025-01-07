package com.example.filmia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class MyMovies(val mname:String,val mposter:Int)

class MainActivity : AppCompatActivity() {

    lateinit var recView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recView = findViewById(R.id.my_movie_recyclerview)

        val mydata:MutableList<MyMovies> = prepareData()

        // Access your data from adapter
        val mfmda:MyMovieAdapter = MyMovieAdapter(this,mydata)
        //Calling Adpter
        recView.adapter = mfmda

        // Set layout
        recView.layoutManager = LinearLayoutManager(this)

//        mfmda.setOnItemClickListener()
    }

    private fun prepareData(): MutableList<MyMovies> {

        val l:MutableList<MyMovies> = mutableListOf()
        l.add(MyMovies("Antim",R.drawable.antim))
        l.add(MyMovies("Bodyguard",R.drawable.bodyguard))
        l.add(MyMovies("Dabang3",R.drawable.dabang3))
        l.add(MyMovies("Hum Apke Hai Koun",R.drawable.hum_apke_hai_koun))
        l.add(MyMovies("Hum Dil De Chuke Sanam",R.drawable.hum_apke_hai_koun))
        l.add(MyMovies("Kick",R.drawable.kick))
        l.add(MyMovies("Kisi Ka Bhai Kisi Ka Jaan",R.drawable.kisi_bhai_kisi_ka_jaan))
        l.add(MyMovies("Race3",R.drawable.race3))
        l.add(MyMovies("Sultaan",R.drawable.sultaan))
        l.add(MyMovies("Veer",R.drawable.veer))
        return l
    }

}