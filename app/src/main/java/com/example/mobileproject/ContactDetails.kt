package com.example.mobileproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_contact_details.*

class ContactDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide() // Hide action bar
        setContentView(R.layout.activity_contact_details)
        inputInit()

        contact_details_back_btn.setOnClickListener {  // Action after BACK button pressed
            startActivity(Intent(this, MainActivity::class.java))
        }
        contact_details_commit_save_details.setOnClickListener {  // Action after SAVE button pressed
            saveDetails()
        }
        contact_details_commit_delete_contact.setOnClickListener {  // Action after DELETE contact button pressed
            deleteContact()
        }

    }

    private fun inputInit() {  // Prepare required data every non single input data
        val contactTypes = resources.getStringArray(R.array.contact_type)

        if (contact_details_contact_type != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                contactTypes)
            contact_details_contact_type.adapter = adapter
        }

    }

    private fun saveDetails() {  // Save any change of contact details.
        // Possible action: UPDATE, DELETE
        val datas = "FName = ${contact_details_fname.text}\n" +
                "LName = ${contact_details_lname.text}\n" +
                "Email = ${contact_details_email.text}\n" +
                "Phone = ${contact_details_phone_number.text}\n" +
                "Type = ${contact_details_contact_type.selectedItem}" +
                "Image = !configured"
        Toast.makeText(this, datas, Toast.LENGTH_SHORT).show()
    }

    private fun deleteContact() {  // Delete current displayed contact from database table
        Toast.makeText(this, "Deleting Contact [ID]", Toast.LENGTH_SHORT).show()

        startActivity(Intent(this, MainActivity::class.java))
    }
}