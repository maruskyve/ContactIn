package com.example.mobileproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobileproject.networking.*

var loginStat = false  // Init key value

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide() // Hide action bar

        if (loginStat) {  // Checking if user already login or !
            setContentView(R.layout.activity_main)
        } else {
            requireLogin()
        }

    }
    private fun requireLogin() {
        val loginIntent = Intent(this, UserLogin::class.java)
        loginIntent.putExtra("loginStat", loginStat)
        startActivity(loginIntent)
    }
}