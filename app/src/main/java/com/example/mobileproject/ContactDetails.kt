package com.example.mobileproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ContactDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)
        supportActionBar?.hide() // Hide action bar

    }
}