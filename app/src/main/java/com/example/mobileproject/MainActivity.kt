package com.example.mobileproject

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Bundle
import android.text.format.Formatter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mobileproject.networking.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val mainHomepage = MainHomepage()
    private val mainContactList = MainContactList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide() // Hide action bar
//        Toast.makeText(this, "${LoginState().getState()}", Toast.LENGTH_SHORT).show()
        apiInit()

        if (LoginState().getState()) {  // Checking if user already login or !
            setContentView(R.layout.activity_main)
            contentMain()
        } else {
            requireLogin()
        }

    }

    private fun apiInit() {
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val ipAddress: String = Formatter.formatIpAddress(wifiManager.connectionInfo.ipAddress)

        Toast.makeText(this, ipAddress, Toast.LENGTH_SHORT).show()

        ApiEndPoint.setAddress(ipAddress)
    }
    // Main content, layout replaced by fragment
    private fun contentMain() {
        fun fragmentInit(fragment : Fragment) {  // Load fragment to pre-initialized LinearLayout
            // Add contact list fragment to this activity
            val fr = supportFragmentManager.beginTransaction()
            fr.replace(R.id.main_fragment_container, fragment).commit()
        }
//        Toast.makeText(this, "${SavedFragment().getFragment()}", Toast.LENGTH_SHORT).show()

        fragmentInit(mainContactList)
        main_commit_view_contact_list.setOnClickListener {
            fragmentInit(mainContactList)
            SavedFragment().setFragment(mainContactList)
        }
        main_commit_view_homepage.setOnClickListener {
            fragmentInit(mainHomepage)
            SavedFragment().setFragment(mainHomepage)
        }

    }

    // Login required if loginStatus is false
    private fun requireLogin() {
        val loginIntent = Intent(this, UserLogin::class.java)
        loginIntent.putExtra("loginStat", LoginState().getState())
        startActivity(loginIntent)
    }
}