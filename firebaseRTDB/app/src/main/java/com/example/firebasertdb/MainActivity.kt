package com.example.firebasertdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var mail:EditText
    lateinit var password:EditText
    lateinit var name:EditText
    lateinit var age:EditText
    lateinit var result:TextView
    lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.person_name)
        age = findViewById(R.id.person_age)
        mail = findViewById(R.id.person_mail)
        password = findViewById(R.id.password)
        result = findViewById(R.id.result)

        // Creating ref variable for RealTime Database
        reference = Firebase.database.getReference()

        name.clearFocus()
        age.clearFocus()
        mail.clearFocus()
        password.clearFocus()
        name.text.clear()
        age.text.clear()
        mail.text.clear()
        password.text.clear()

    }

    fun loadData(view: View) {

        // Retrieving a data from Firebase database
        reference.child("User").addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val stringBuilder = StringBuilder()
                for (childSnapshot in snapshot.children){
                    val personData = childSnapshot.getValue(PersonData::class.java)
                    stringBuilder.append("Name : ${personData?.name}\nAge :${personData?.age}\nE-Mail : ${personData?.mail}\nPassword :${personData?.pass}\n")
                }
                result.text=stringBuilder.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handling Errors
                result.text ="Error While Loading data from firebase: ${error.message}"
            }

        })

    }
    fun saveData(view: View) {

        val n = name.text.toString()
        val a = age.text.toString().toInt()
        val m = mail.text.toString()
        val p = password.text.toString()
         // Writing a logic for saving a data
        reference.child("User").push().setValue(PersonData(n,a,m,p))

        // Make Sure after entering value the EditText must be empty
        name.clearFocus()
        age.clearFocus()
        mail.clearFocus()
        password.clearFocus()
        name.text.clear()
        age.text.clear()
        name.clearFocus()
        age.clearFocus()

    }
}