package com.example.mobileproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_group_details.*

class GroupDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_details)
        supportActionBar?.hide() // Hide action bar

        Toast.makeText(this, "${SavedFragment().getFragment()}", Toast.LENGTH_SHORT).show()
        group_details_commit_back.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}