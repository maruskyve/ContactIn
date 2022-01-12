package com.example.mobileproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mobileproject.datas.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val mainHomepage = MainHomepage()
    private val mainContactList = MainContactList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide() // Hide action bar
        DataPrep().fetchContactTypeData()

        if (SESSION_LOGIN) {  // Checking if user already login or !
            DataPrep().fetchContactData()
            setContentView(R.layout.activity_main)
            contentMain()
        } else {
            requireLogin()
        }
    }

    // Replacing layout to fragment
    private fun contentMain() {
        fun fragmentInit(fragment : Fragment) {  // Load fragment to pre-initialized LinearLayout
            // Add contact list fragment to this activity
            val fr = supportFragmentManager.beginTransaction()
            fr.replace(R.id.main_fragment_container, fragment).commit()
        }

        fragmentInit(mainContactList)
        main_commit_view_contact_list.setOnClickListener {  // Action after contact list btn pressed
            fun fragmentInit(fragment : Fragment) {  // Load fragment to pre-initialized LinearLayout
                val fr = supportFragmentManager.beginTransaction()
                fr.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right)
                fr.replace(R.id.main_fragment_container, fragment).commit()
            }
            fragmentInit(mainContactList)
        }

        main_commit_add_contact.setOnClickListener {  // Action after ADD contact btn pressed
            startActivity(Intent(this, CreateContact::class.java))
        }

        main_commit_view_homepage.setOnClickListener {  // Homepage button
            fun fragmentInit(fragment : Fragment) {  // Load fragment to pre-initialized LinearLayout
                val fr = supportFragmentManager.beginTransaction()
                fr.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right)
                fr.replace(R.id.main_fragment_container, fragment).commit()
            }
            fragmentInit(mainHomepage)
        }
    }

    // Login required if loginStatus is false
    private fun requireLogin() {
        startActivity(Intent(this, UserLogin::class.java))
    }
}
