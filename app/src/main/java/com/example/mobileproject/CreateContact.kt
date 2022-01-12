package com.example.mobileproject

import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.mobileproject.datas.contactTypeData
import com.example.mobileproject.networking.ApiEndPoint
import kotlinx.android.synthetic.main.activity_contact_details.*
import kotlinx.android.synthetic.main.activity_create_contact.*
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CreateContact : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_contact)
        supportActionBar?.hide() // Hide action bar
        inputInit()

        create_contact_back_btn.setOnClickListener {  // Action after BACK button pressed
            startActivity(Intent(this, MainActivity::class.java))
        }

        create_contact_commit_save_contact.setOnClickListener {  // Action after SAVE button pressed
            saveContact()
        }
    }

    private fun inputInit() {  // Initializer for multi choice input
        var contactTypeName = arrayListOf<String>()
        for (x in contactTypeData) {
            contactTypeName.add(x.contactTypeName)
        }

        if (create_contact_contact_type != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                contactTypeName)
            create_contact_contact_type.adapter = adapter
            create_contact_contact_type.setSelection(0)
        }
    }

    // CREATE CONTACT
    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveContact() {  // Save all entered data from user (CREATE).
        // Data prep
        val idV = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"))+(100..999).random().toString()
        val phoneNumberV = create_contact_phone_number.text.toString()
        val emailV = create_contact_email.text.toString()
        val fnameV = create_contact_fname.text.toString()
        val lnameV = create_contact_lname.text.toString()
        val ppictureV = if (fnameV.isNotEmpty() && lnameV.isNotEmpty())  // Empty name field handler
            fnameV.first().uppercase()+lnameV.first().uppercase() else ""
        val starsV = "0"
        val fkContactTypeIdV = (create_contact_contact_type.selectedItemId+1).toString()
        val fkUseridV = SESSION_USER_ID

        fun processRequest() {
            val loading = ProgressDialog(this)
            loading.setMessage("Adding Contact . . .")
            loading.show()

            AndroidNetworking.post(ApiEndPoint.CONTACT_CREATE_CONTACT)
                .addBodyParameter("contact_id", idV)
                .addBodyParameter("contact_phone_number", phoneNumberV)
                .addBodyParameter("contact_email", emailV)
                .addBodyParameter("contact_fname", fnameV)
                .addBodyParameter("contact_lname", lnameV)
                .addBodyParameter("contact_ppicture", ppictureV)
                .addBodyParameter("contact_stars", starsV)
                .addBodyParameter("fk_contact_type_id", fkContactTypeIdV)
                .addBodyParameter("fk_user_id", fkUseridV)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        loading.dismiss()
                        Toast.makeText(
                            applicationContext,
                            response?.getString("message"),
                            Toast.LENGTH_SHORT
                        ).show()

                        if (response?.getString("message")?.contains("successfully")!!) {
                            startActivity(Intent(this@CreateContact, MainActivity::class.java))
                        }
                    }

                    override fun onError(anError: ANError?) {
                        loading.dismiss()
                        Log.d("ON ERROR", anError?.errorDetail.toString())
                        Toast.makeText(applicationContext, "Failure", Toast.LENGTH_SHORT).show()
                    }
                })
        }
        processRequest()
    }
}
