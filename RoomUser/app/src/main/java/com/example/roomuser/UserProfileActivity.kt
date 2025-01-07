package com.example.roomuser

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserProfileActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val db = UserDatabase.getDatabase(this)
        userDao = db.userDao()

        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPhone = findViewById<EditText>(R.id.editTextPhone)
        val editTextGender = findViewById<EditText>(R.id.editTextGender)
        val buttonSave = findViewById<Button>(R.id.buttonSave)

        buttonSave.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val phone = editTextPhone.text.toString().trim()
            val gender = editTextGender.text.toString().trim()

            if (name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty() && gender.isNotEmpty()) {
                val newUser = User(name = name, email = email, phone = phone, gender = gender)

                lifecycleScope.launch {
                    val id = userDao.insert(newUser)
                    if (id > 0) {
                        runOnUiThread {
                            Toast.makeText(this@UserProfileActivity, "User added successfully with ID: $id", Toast.LENGTH_SHORT).show()
                            clearFields()
                        }
                    } else {
                        runOnUiThread {
                            Toast.makeText(this@UserProfileActivity, "Error adding user", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearFields() {
        findViewById<EditText>(R.id.editTextName).text.clear()
        findViewById<EditText>(R.id.editTextEmail).text.clear()
        findViewById<EditText>(R.id.editTextPhone).text.clear()
        findViewById<EditText>(R.id.editTextGender).text.clear()
    }
}


