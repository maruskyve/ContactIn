package com.example.mobileproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ContactDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide() // Hide action bar
        setContentView(R.layout.activity_contact_details)

    }
}