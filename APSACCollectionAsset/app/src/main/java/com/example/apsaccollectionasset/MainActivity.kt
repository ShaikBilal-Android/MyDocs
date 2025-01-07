package com.example.apsaccollectionasset


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView


class MainActivity : AppCompatActivity() {

    private lateinit var grideView:GridView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        grideView = findViewById(R.id.my_gridview)

        val data = listOf(
            MyGridAdapter.MyDataItem(R.drawable.soil,"Agriculture And soil"),
            MyGridAdapter.MyDataItem(R.drawable.earthquake,"Disaster Management & Coastal Forest"),
            MyGridAdapter.MyDataItem(R.drawable.geomatics,"Geomagnetic And E-Governance"),
            MyGridAdapter.MyDataItem(R.drawable.gis,"Urban And Ecology"),
            MyGridAdapter.MyDataItem(R.drawable.gis,"Geo Science And Land Use"),
            MyGridAdapter.MyDataItem(R.drawable.water,"Water Resource"),
            MyGridAdapter.MyDataItem(R.drawable.technological,"Servay Advanced Techniques"),
            MyGridAdapter.MyDataItem(R.drawable.town,"Capacity Building Policy And Partnership"),
        )

        val adapter = MyGridAdapter(this,data)
        grideView.adapter = adapter
    }
}