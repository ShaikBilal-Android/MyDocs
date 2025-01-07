package com.example.studenttask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    lateinit var studentName:TextView
    lateinit var studentRollnumb:TextView
    lateinit var pic:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentName = findViewById(R.id.textView)
        studentRollnumb =findViewById(R.id.s_rolll_Numbr)
        pic = findViewById(R.id.imageView)



    }

    fun LoadData(view: View) {
        val intent = Intent(this,AddStudents::class.java)
        startActivity(intent)
    }
}