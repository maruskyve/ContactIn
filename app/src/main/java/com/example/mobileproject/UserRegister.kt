package com.example.mobileproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_user_register.*

class UserRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide() // Hide action bar
        setContentView(R.layout.activity_user_register)

        register_back_btn.setOnClickListener {  // Action after top bar BACK button clicked
            startActivity(Intent(this, UserLogin::class.java))
        }

        register_commit_btn.setOnClickListener {  // Action after REGISTER button clicked
            registerValidation()
        }
    }

    private fun registerValidation() {  // temp - Checking registration data entered
        val genderId = register_gender_rg.checkedRadioButtonId
        val gender = findViewById<RadioButton>(genderId).text.toString()
        Toast.makeText(this, "Checking your data\n" +
                "FName = ${register_fname.text}\n" +
                "LName = ${register_lname.text}\n" +
                "Gender = $gender\n" +
                "Phone = ${register_phone_number.text}\n" +
                "Email = ${register_email.text}", Toast.LENGTH_SHORT).show()
    }
}