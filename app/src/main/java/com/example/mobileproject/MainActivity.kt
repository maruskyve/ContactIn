package com.example.mobileproject

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Bundle
import android.text.format.Formatter
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.mobileproject.datas.ContactTypeData
import com.example.mobileproject.datas.UserData
import com.example.mobileproject.networking.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.lang.Exception
import java.net.InetAddress

class MainActivity : AppCompatActivity() {
    private val mainHomepage = MainHomepage()
    private val mainContactList = MainContactList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide() // Hide action bar
//        Toast.makeText(this, "${LoginState().getState()}", Toast.LENGTH_SHORT).show()
        apiInit()
        fetchContactType()

        if (SESSION_LOGIN) {  // Checking if user already login or !
            setContentView(R.layout.activity_main)
            contentMain()
        } else {
            requireLogin()
        }

    }

    // Initialize current host IP address for ApiEndPoint prequisite
    private fun apiInit() {
        try {
            val localAddress = InetAddress.getLocalHost()
            val ipAddress = localAddress.hostAddress
            Log.i("IP ADDRESS", ipAddress)
        } catch (e : Exception) {
            Log.e("", e.toString())
        }
    }

    //
    private fun fetchContactType() {
        val contactTypeArray = ArrayList<ContactTypeData>()

        AndroidNetworking.post(ApiEndPoint.CONTACT_TYPE_READ)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    val jsonArray = response?.optJSONArray("result")

                    if (jsonArray?.length() == 0) {
                        Log.i("", "Contact Data Type is empty")
                    } else {
                        for (i in 0 until jsonArray?.length()!!) {
                            val jsonObject = jsonArray.optJSONObject(i)
                            contactTypeArray.add(
                                ContactTypeData(
                                    jsonObject.getString("contact_type_id"),
                                    jsonObject.getString("contact_type_name")
                                )
                            )

                            if (jsonArray.length() - 1 == i) {
                                Log.i("", "Data fetch complete")
                            }
                        }
                        Log.i("", "Contact Data Type found")
                        Log.i("Contact Type Data", contactTypeArray.toString())
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.e("Failure", anError?.errorDetail.toString())
                }
            })

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
            fragmentInit(mainContactList)
        }

        main_commit_add_contact.setOnClickListener {  // Action after ADD contact btn pressed
            startActivity(Intent(this, CreateContact::class.java))
        }

        main_commit_view_homepage.setOnClickListener {  // Homepage button
            fragmentInit(mainHomepage)
        }

    }

    // Login required if loginStatus is false
    private fun requireLogin() {
        startActivity(Intent(this, UserLogin::class.java))
    }
}