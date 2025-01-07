package com.example.studenttask

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.studenttask.Models.StudentModel
import com.example.studenttask.databinding.ActivityAddStudentsBinding
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class AddStudents : AppCompatActivity() {

    val binding by lazy {
        ActivityAddStudentsBinding.inflate(layoutInflater)
    }
    val folderName:String = "Profile"
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
        uri->uri?.let {
            uploadimg(uri,folderName){

            }else{studentdata =it}
    }
    }
    lateinit var studentdata:StudentModel
    lateinit var stuname:EditText
    lateinit var stuphone:EditText
    lateinit var spin:Spinner
    lateinit var sturollNo:EditText
    lateinit var uploadpic:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        studentdata = StudentModel()

        stuname =findViewById(R.id.edittext_s_name)
        stuphone = findViewById(R.id.edittext_s_phone)
        sturollNo = findViewById(R.id.edittext_s_rollnumber)
        spin = findViewById(R.id.c_spinner)
        uploadpic = findViewById(R.id.pic)

//            studentdata.nam =
//            studentdata.phonenumb =
//            studentdata.rollnumb =
//            studentdata.img =

//        // Example data for the Spinner
//        val spinnerItems = listOf("Class 1", "Class 2", "Class 3", "Class 4", "Class 5")
//
//        // Create an ArrayAdapter using the string array and a default spinner layout
//        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
//
//        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//
//        // Apply the adapter to the spinner
//        spin.adapter = adapter


        // Populate Spinner with items from string-array resource
        val spinnerItems = resources.getStringArray(R.array.studentclass)

        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        spin.adapter = adapter


    }

    fun saveData(view: View) {
        Toast.makeText(this,"The data Saved Succesfully",Toast.LENGTH_LONG).show()
    }
    // Function to upload image
    private fun uploadimg(uri: Uri, folderName: String) {
        val imageURL: String? = null

        FirebaseStorage.getInstance().getReference(folderName)
            .child(UUID.randomUUID().toString())
            .putFile(uri)
            .addOnSuccessListener { taskSnapshot ->
                taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                    // Get the download URL
                    val imageURL = it.toString()
                    // Handle imageURL as needed
                }
            }
            .addOnFailureListener { exception ->
                // Handle upload failure
                exception.printStackTrace()
            }
    }
}